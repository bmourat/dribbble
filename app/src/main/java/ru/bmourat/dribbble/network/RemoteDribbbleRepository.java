package ru.bmourat.dribbble.network;

import java.util.List;

import ru.bmourat.dribbble.mvp.model.DribbbleRepository;
import ru.bmourat.dribbble.mvp.model.Settings;
import ru.bmourat.dribbble.network.model.Shot;
import rx.Observable;

/**
 * Created by BM on 1/24/17.
 */

public class RemoteDribbbleRepository implements DribbbleRepository {

	private Settings settings;
	private DribbbleApiService apiService;

	public RemoteDribbbleRepository(DribbbleApiService apiService, Settings settings){
		this.apiService = apiService;
		this.settings = settings;
	}

	@Override
	public Observable<List<Shot>> getShots(int page) {
		return Observable.fromCallable(() -> apiService.getShots(page,
				settings.getNumberOfShotsPerPage(),
				settings.getClientAccessToken())
			.execute().body()
		);
	}
}
