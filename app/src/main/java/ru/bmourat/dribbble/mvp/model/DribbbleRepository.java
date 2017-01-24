package ru.bmourat.dribbble.mvp.model;

import java.util.List;

import ru.bmourat.dribbble.network.model.Shot;
import rx.Observable;

/**
 * Created by BM on 1/24/17.
 */

public interface DribbbleRepository {
	Observable<List<Shot>> getShots(int page);
}
