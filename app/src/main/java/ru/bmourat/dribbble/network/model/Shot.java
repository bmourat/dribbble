package ru.bmourat.dribbble.network.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by BM on 1/24/17.
 */

@AutoValue
public abstract class Shot {

	public abstract String image();
	public abstract String title();
	public abstract String description();

	public static JsonAdapter<Shot> jsonAdapter(Moshi moshi) {
		return new AutoValue_Shot.MoshiJsonAdapter(moshi);
	}
}

