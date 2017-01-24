package ru.bmourat.dribbble.helper;

import android.databinding.BindingAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by BM on 10/27/16.
 */

public final class BindingUtil {
	private BindingUtil(){}

	@BindingAdapter("bind:imageUrl")
	public static void loadImage(SimpleDraweeView draweeView, String url) {
		draweeView.setImageURI(url);
	}
}
