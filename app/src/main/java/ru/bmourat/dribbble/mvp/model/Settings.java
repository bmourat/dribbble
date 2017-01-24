package ru.bmourat.dribbble.mvp.model;

/**
 * Created by BM on 1/24/17.
 */

public class Settings {
	private int numberOfShotsPerPage;
	private String clientAccessToken;
	public Settings(String clientAccessToken, int numberOfShotsPerPage) {
		this.clientAccessToken = clientAccessToken;
		this.numberOfShotsPerPage = numberOfShotsPerPage;
	}

	public String getClientAccessToken(){
		return clientAccessToken;
	}

	public int getNumberOfShotsPerPage(){
		return numberOfShotsPerPage;
	}
}
