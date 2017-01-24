package ru.bmourat.dribbble.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.bmourat.dribbble.mvp.model.DribbbleRepository;

/**
 * Created by BM on 1/24/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
	DribbbleRepository getDribbbleRepository();
}
