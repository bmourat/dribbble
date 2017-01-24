package ru.bmourat.dribbble.network.model;

import com.android.annotations.Nullable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by BM on 1/24/17.
 */

@AutoValue
public abstract class Shot {

	public abstract Long id();

	public abstract String title();
	@Nullable
	public abstract String description();

	public abstract ImageContainer images();

	public static JsonAdapter<Shot> jsonAdapter(Moshi moshi) {
		return new AutoValue_Shot.MoshiJsonAdapter(moshi);
	}
}

