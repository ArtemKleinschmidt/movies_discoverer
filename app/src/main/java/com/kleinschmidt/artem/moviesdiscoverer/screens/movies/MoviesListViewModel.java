package com.kleinschmidt.artem.moviesdiscoverer.screens.movies;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.MoviesRepository;
import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.MoviesRepositoryImpl;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.ResultsContainer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

public class MoviesListViewModel extends ViewModel {

    private static final String TAG = "MoviesListViewModel";
    private final MutableLiveData<ResultsContainer> resultsContainerLiveData;
    private MoviesRepository moviesRepository;
    private CompositeDisposable compositeDisposable;

    public MoviesListViewModel() {
        resultsContainerLiveData = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }

    MutableLiveData<ResultsContainer> getResultsContainerLiveData() {
        return resultsContainerLiveData;
    }

    void loadData() {
        Disposable disposable = getOrCreateMoviesRepository().getPopularMovies().subscribe(resultsContainer -> {
            resultsContainerLiveData.setValue(resultsContainer);
            Log.d(TAG, "onSuccess: " + resultsContainer);
        }, throwable -> {
            Log.e(TAG, "onError: ", throwable);
        });
        compositeDisposable.add(disposable);
    }

    private MoviesRepository getOrCreateMoviesRepository() {
        if (moviesRepository == null) {
            moviesRepository = new MoviesRepositoryImpl();
        }
        return moviesRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
