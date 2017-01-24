package ru.bmourat.dribbble.network.moshi;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

@MoshiAdapterFactory
public abstract class DribbbleMoshiAdapterFactory implements JsonAdapter.Factory {

    public static JsonAdapter.Factory create() {
        return new AutoValueMoshi_DribbbleMoshiAdapterFactory();
    }

}

