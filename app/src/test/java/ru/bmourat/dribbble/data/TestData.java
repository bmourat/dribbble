package ru.bmourat.dribbble.data;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import ru.bmourat.dribbble.network.model.Shot;
import ru.bmourat.dribbble.network.moshi.DribbbleMoshiAdapterFactory;

/**
 * Created by BM on 1/24/17.
 */

public class TestData {

	public static List<Shot> SHOT_LIST;
	public static List<Shot> SHOT_LIST_NOT_ANIMATED;
	public static List<Shot> SHOT_LIST_ANIMATED;

	static {
		Moshi moshi = new Moshi.Builder().add(DribbbleMoshiAdapterFactory.create()).build();
		Type shotList = Types.newParameterizedType(List.class, Shot.class);
		JsonAdapter<List<Shot>> shotListAdapter = moshi.adapter(shotList);
		try {
			SHOT_LIST_NOT_ANIMATED = shotListAdapter.fromJson(TestDataStrings.SHOT_LIST_NOT_ANIMATED);
			SHOT_LIST_ANIMATED = shotListAdapter.fromJson(TestDataStrings.SHOT_LIST_ANIMATED);
			SHOT_LIST = shotListAdapter.fromJson(TestDataStrings.SHOT_LIST);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
