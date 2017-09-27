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
    private MutableLiveData<ResultsContainer> resultsContainerLiveData;
    private VideosRepository videosRepository;
    private CompositeDisposable compositeDisposable;

    MutableLiveData<ResultsContainer> getPopularVideos() {
        Log.d(TAG, "getPopularVideos() called");
        if (resultsContainerLiveData == null) {
            resultsContainerLiveData = new MutableLiveData<>();
            loadPopularVideos();
        }
        return resultsContainerLiveData;
    }

    private void loadPopularVideos() {
        Disposable disposable = getOrCreateMoviesRepository().getPopularMovies().subscribe(resultsContainer -> {
            resultsContainerLiveData.setValue(resultsContainer);
            Log.d(TAG, "onSuccess: " + resultsContainer);
        }, throwable -> {
            Log.e(TAG, "onError: ", throwable);
        });
        getOrCreateCompositeDisposable().add(disposable);
    }

    private VideosRepository getOrCreateMoviesRepository() {
        if (videosRepository == null) {
            videosRepository = new VideosRepositoryImpl();
        }
        return videosRepository;
    }

    private CompositeDisposable getOrCreateCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
