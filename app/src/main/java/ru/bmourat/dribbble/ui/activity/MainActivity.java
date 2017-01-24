package ru.bmourat.dribbble.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.bmourat.dribbble.R;
import ru.bmourat.dribbble.helper.FragmentStack;
import ru.bmourat.dribbble.ui.fragment.ShotListFragment;

public class MainActivity extends AppCompatActivity {

	private FragmentStack fragmentStack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fragmentStack = new FragmentStack(getFragmentManager(), R.id.fragment);
		if(savedInstanceState == null){
			fragmentStack.replace(ShotListFragment.newInstance());
		}
	}
}
