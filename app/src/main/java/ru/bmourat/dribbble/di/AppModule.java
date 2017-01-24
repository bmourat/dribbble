package ru.bmourat.dribbble.di;

import android.content.Context;

import dagger.Module;

/**
 * Created by BM on 1/24/17.
 */

@Module
public class AppModule {

	private Context context;

	public AppModule(Context context) {
		this.context = context;
	}
}
