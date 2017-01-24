package ru.bmourat.dribbble.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.bmourat.dribbble.databinding.ItemShotBinding;
import ru.bmourat.dribbble.network.model.Shot;

/**
 * Created by BM on 1/24/17.
 */

public class ShotListAdapter extends RecyclerView.Adapter<ShotListAdapter.ShotViewHolder> {

	private List<Shot> shotList = new ArrayList<>();

	public void addShots(List<Shot> items){
		shotList.addAll(items);
		notifyDataSetChanged();
	}
	public void replaceShots(List<Shot> items) {
		shotList.clear();
		shotList.addAll(items);
		notifyDataSetChanged();
	}

	@Override
	public ShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		ItemShotBinding binding = ItemShotBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
		return new ShotViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(ShotViewHolder holder, int position) {
		holder.bind(shotList.get(position));
	}

	@Override
	public int getItemCount() {
		return shotList.size();
	}

	class ShotViewHolder extends RecyclerView.ViewHolder{

		private ItemShotBinding binding;

		ShotViewHolder(ItemShotBinding binding) {
			super(binding.getRoot());
			this.binding = binding;
		}

		void bind(Shot shot){
			binding.setShot(shot);
		}
	}
}
