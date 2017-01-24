package ru.bmourat.dribbble.shotlist;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import ru.bmourat.dribbble.data.TestData;
import ru.bmourat.dribbble.mvp.model.DribbbleRepository;
import ru.bmourat.dribbble.mvp.presenter.ShotListPresenter;
import ru.bmourat.dribbble.mvp.view.ShotListView;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by BM on 1/24/17.
 */

public class ShotListPresenterTest {

	@Mock
	ShotListView view;

	@Mock
	DribbbleRepository repository;

	private ShotListPresenter presenter;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		presenter = new ShotListPresenter(repository, Schedulers.immediate(), Schedulers.immediate());
		presenter.attachView(view);
	}

	@Test
	public void shouldLoadShotsFromRepositoryAndLoadIntoView(){
		//Arrange
		final int pageToLoad = 1;
		final boolean animated = false;
		when(repository.getShots(pageToLoad)).thenReturn(Observable.just(TestData.SHOT_LIST_NOT_ANIMATED));

		//Act
		presenter.loadShotList(pageToLoad, animated);

		//Assert
		verify(repository).getShots(pageToLoad);

		InOrder inOrder = inOrder(view);

		inOrder.verify(view).showLoading(true);
		inOrder.verify(view).showShotList(TestData.SHOT_LIST_NOT_ANIMATED);
		inOrder.verify(view).showLoading(false);
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	public void shouldLoadShotsAndFilterAnimated(){
		//Arrange
		final int pageToLoad = 1;
		final boolean animated = false;
		when(repository.getShots(pageToLoad)).thenReturn(Observable.just(TestData.SHOT_LIST_ANIMATED));

		//Act
		presenter.loadShotList(pageToLoad, animated);

		//Assert;
		verify(view).showShotList(new ArrayList<>());
	}

	@Test
	public void shouldLoadShotsAndFilterNotAnimated(){
		//Arrange
		final int pageToLoad = 1;
		final boolean animated = false;
		when(repository.getShots(pageToLoad)).thenReturn(Observable.just(TestData.SHOT_LIST));

		//Act
		presenter.loadShotList(pageToLoad, animated);

		//Assert
		verify(view).showShotList(Stream.of(TestData.SHOT_LIST).filter(shot -> shot.animated() == animated).collect(Collectors.toList()));
	}

	@Test
	public void shouldCallShowErrorInCaseOfError(){
		//Arrange
		final int pageToLoad = 1;
		final boolean animated = false;
		when(repository.getShots(pageToLoad)).thenReturn(Observable.error(new RuntimeException("Something gone wrong")));

		//Act
		presenter.loadShotList(pageToLoad, animated);

		//Assert
		verify(view).showError(any());
	}
}
