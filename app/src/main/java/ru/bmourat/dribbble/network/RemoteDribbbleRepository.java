package ru.bmourat.dribbble.network;

import java.util.List;

import ru.bmourat.dribbble.mvp.model.DribbbleRepository;
import ru.bmourat.dribbble.network.model.Shot;
import rx.Observable;

/**
 * Created by BM on 1/24/17.
 */

public class RemoteDribbbleRepository implements DribbbleRepository {

	private String clientAccessToken;
	private DribbbleApiService apiService;

	public RemoteDribbbleRepository(DribbbleApiService apiService, String clientAccessToken){
		this.apiService = apiService;
		this.clientAccessToken = clientAccessToken;
	}

	@Override
	public Observable<List<Shot>> getShots() {
		return Observable.fromCallable(() -> apiService.getShots(clientAccessToken).execute().body());
	}
}
