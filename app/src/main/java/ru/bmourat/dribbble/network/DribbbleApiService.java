package ru.bmourat.dribbble.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.bmourat.dribbble.network.model.Shot;

/**
 * Created by BM on 1/24/17.
 */

public interface DribbbleApiService {

	String baseAddress = "https://api.dribbble.com/v1/";

	@GET("shots")
	Call<List<Shot>> getShots(@Query("access_token") String accessToken);
}
