package ru.bmourat.dribbble.helper;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import rx.Observable;
import rx.subjects.PublishSubject;


public class PaginationTool {

	private RecyclerView recyclerView;
	private int pageSize;
	private int currentPage;
	private PublishSubject<Integer> pagingSubject = PublishSubject.create();

	public PaginationTool(RecyclerView recyclerView, int startPage, int pageSize) {
		this.recyclerView = recyclerView;
		this.pageSize = pageSize;
		this.currentPage = startPage;

		recyclerView.addOnScrollListener(scrollListener);
	}



	public Observable<Integer> getPagingObservable(){
		return pagingSubject.distinctUntilChanged();
	}

	private int calculateCurrentPage(){
		return currentPage;
		//return recyclerView.getAdapter().getItemCount() / pageSize;
	}

	private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

			if (dy > 0) {
				LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
				int visibleItemCount = layoutManager.getChildCount();
				int totalItemCount = layoutManager.getItemCount();
				int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

				if ((visibleItemCount + pastVisibleItems) >= (totalItemCount - pageSize / 3)) {
					pagingSubject.onNext(calculateCurrentPage());
				}

			}

		}
	};

	public int getCurrentPage() {
		return currentPage;
	}

	public void incrementCurrentPage() {
		currentPage++;
	}
}