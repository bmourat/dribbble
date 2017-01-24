package ru.bmourat.dribbble.app;

import android.app.Application;

import ru.bmourat.dribbble.di.AppComponent;
import ru.bmourat.dribbble.di.AppModule;
import ru.bmourat.dribbble.di.DaggerAppComponent;

/**
 * Created by BM on 1/24/17.
 */

public class DribbbleApp extends Application {

	private static AppComponent appComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
	}
}
