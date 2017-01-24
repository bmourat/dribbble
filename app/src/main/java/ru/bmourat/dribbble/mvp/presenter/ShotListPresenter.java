package ru.bmourat.dribbble.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.MvpPresenter;

import ru.bmourat.dribbble.mvp.model.DribbbleRepository;
import ru.bmourat.dribbble.mvp.view.ShotListView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by BM on 1/24/17.
 */

public class ShotListPresenter extends MvpPresenter<ShotListView> {

	public static final String ID = "ShotListPresenter";

	private DribbbleRepository repository;

	public ShotListPresenter(DribbbleRepository repository) {
		this.repository = repository;
	}

	public void loadShotList(){
		repository.getShots()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.doOnSubscribe(() -> getViewState().showLoading(true))
			.doOnTerminate(() -> getViewState().showLoading(false))
			.subscribe(
				shotList -> getViewState().showShotList(shotList),
				error -> Log.e(ID, "Error fetching shot list",error)
			);
	}
}
