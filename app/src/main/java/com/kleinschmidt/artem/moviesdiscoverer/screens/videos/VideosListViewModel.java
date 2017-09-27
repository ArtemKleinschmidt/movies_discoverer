package com.kleinschmidt.artem.moviesdiscoverer.screens.videos;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.VideosRepository;
import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.VideosRepositoryImpl;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.ResultsContainer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

public class VideosListViewModel extends ViewModel {

    private static final String TAG = "VideosListViewModel";
    private final MutableLiveData<ResultsContainer> resultsContainerLiveData;
    private VideosRepository videosRepository;
    private CompositeDisposable compositeDisposable;

    public VideosListViewModel() {
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

    private VideosRepository getOrCreateMoviesRepository() {
        if (videosRepository == null) {
            videosRepository = new VideosRepositoryImpl();
        }
        return videosRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
