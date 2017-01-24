package ru.bmourat.dribbble.network.model;

import com.android.annotations.Nullable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by BM on 1/24/17.
 */
@AutoValue
public abstract class ImageContainer {

	@Nullable
	public abstract String hidpi();
	@Nullable
	public abstract String normal();
	@Nullable
	public abstract String teaser();

	public String image(){
		return hidpi() != null ? hidpi() : normal();
	}

	public static JsonAdapter<ImageContainer> jsonAdapter(Moshi moshi) {
		return new AutoValue_ImageContainer.MoshiJsonAdapter(moshi);
	}
}
