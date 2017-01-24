package ru.bmourat.dribbble.di;

import android.content.Context;

import com.squareup.moshi.Moshi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import ru.bmourat.dribbble.R;
import ru.bmourat.dribbble.mvp.model.DribbbleRepository;
import ru.bmourat.dribbble.mvp.model.Settings;
import ru.bmourat.dribbble.network.DribbbleApiService;
import ru.bmourat.dribbble.network.RemoteDribbbleRepository;
import ru.bmourat.dribbble.network.moshi.DribbbleMoshiAdapterFactory;

/**
 * Created by BM on 1/24/17.
 */

@Module
public class AppModule {

	private Context context;

	public AppModule(Context context) {
		this.context = context;
	}

	@Provides
	@Singleton
	DribbbleApiService provideDribbbleApiService(){

		Moshi moshi = new Moshi.Builder().add(DribbbleMoshiAdapterFactory.create()).build();

		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.connectTimeout(1, TimeUnit.MINUTES)
				.readTimeout(1, TimeUnit.MINUTES)
				.build();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(DribbbleApiService.baseAddress)
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.client(client)
				.build();

		return retrofit.create(DribbbleApiService.class);
	}

	@Provides
	@Singleton
	DribbbleRepository provideDribbbleRepository(DribbbleApiService apiService){
		String clientAccessToken = context.getResources().getString(R.string.client_access_token);
		int numberOfShotsPerPage = context.getResources().getInteger(R.integer.pageSize);
		return new RemoteDribbbleRepository(apiService, new Settings(clientAccessToken, numberOfShotsPerPage));
	}
}
