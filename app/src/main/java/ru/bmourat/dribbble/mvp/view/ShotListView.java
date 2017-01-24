package ru.bmourat.dribbble.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.bmourat.dribbble.network.model.Shot;

/**
 * Created by BM on 1/24/17.
 */

public interface ShotListView extends MvpView {
	void showShotList(List<Shot> shotList);
	void showLoading(boolean visible);
	void showError(String message);
}
