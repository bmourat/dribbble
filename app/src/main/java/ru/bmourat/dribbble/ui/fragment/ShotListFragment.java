package ru.bmourat.dribbble.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import ru.bmourat.dribbble.app.DribbbleApp;
import ru.bmourat.dribbble.databinding.FragmentShotListBinding;
import ru.bmourat.dribbble.mvp.presenter.ShotListPresenter;
import ru.bmourat.dribbble.mvp.view.ShotListView;
import ru.bmourat.dribbble.network.model.Shot;
import ru.bmourat.dribbble.ui.adapter.ShotListAdapter;

/**
 * Created by BM on 1/24/17.
 */

public class ShotListFragment extends BaseFragment implements ShotListView {

	@InjectPresenter(type = PresenterType.LOCAL, tag = ShotListPresenter.ID)
	ShotListPresenter presenter;

	private FragmentShotListBinding binding;
	private ShotListAdapter shotListAdapter = new ShotListAdapter();

	public ShotListFragment() {}

	public static ShotListFragment newInstance() {
		ShotListFragment fragment = new ShotListFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(savedInstanceState == null){
			presenter.loadShotList();
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = FragmentShotListBinding.inflate(inflater, container, false);
		return binding.getRoot();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		binding.rvShotList.setLayoutManager(new LinearLayoutManager(getActivity()));
		binding.rvShotList.setAdapter(shotListAdapter);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	@Override
	public void showShotList(List<Shot> shotList) {
		shotListAdapter.addShots(shotList);
	}

	@Override
	public void showLoading(boolean visible) {
		binding.pbProgress.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

	@ProvidePresenter(type = PresenterType.LOCAL, tag = ShotListPresenter.ID)
	ShotListPresenter provideShotListPresenter(){
		return new ShotListPresenter(DribbbleApp.getAppComponent().getDribbbleRepository());
	}
}
