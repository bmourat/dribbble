package ru.bmourat.dribbble.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import ru.bmourat.dribbble.R;

/**
 * Created by BM on 10/25/16.
 */

public class ColoredProgressBar extends ProgressBar {

	private int color;

	public ColoredProgressBar(Context context) {
		super(context);
		initialize(null);
	}

	public ColoredProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(attrs);
	}

	public ColoredProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize(attrs);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public ColoredProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		initialize(attrs);
	}

	private void initialize(AttributeSet attrs){
		color = ContextCompat.getColor(getContext(), R.color.colorAccent);
		if(attrs != null) {
			TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ColoredProgressBar, 0, 0);
			color = a.getColor(R.styleable.ColoredProgressBar_color, color);
		}

		if(isIndeterminate())
			getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
		else
			getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);

	}
}
