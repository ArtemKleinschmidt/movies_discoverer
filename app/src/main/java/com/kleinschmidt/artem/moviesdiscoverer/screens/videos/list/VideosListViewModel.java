package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;

import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.VideosRepository;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.PerScreenScope;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.ResultsContainer;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */
@PerScreenScope
public class VideosListViewModel extends ViewModel {

    private static final String TAG = "VideosListViewModel";
    private MutableLiveData<ResultsContainer> resultsContainerLiveData;
    private final CompositeDisposable compositeDisposable;
    private final VideosRepository videosRepository;

    @Inject
    VideosListViewModel(CompositeDisposable compositeDisposable, VideosRepository videosRepository) {
        this.compositeDisposable = compositeDisposable;
        this.videosRepository = videosRepository;
    }

    MutableLiveData<ResultsContainer> getPopularVideos() {
        Log.d(TAG, "getPopularVideos() called");
        if (resultsContainerLiveData == null) {
            resultsContainerLiveData = new MutableLiveData<>();
            loadPopularVideos();
        }
        return resultsContainerLiveData;
    }

    private void loadPopularVideos() {
        Disposable disposable = videosRepository.getPopularMovies().subscribe(resultsContainer -> {
            resultsContainerLiveData.setValue(resultsContainer);
            Log.d(TAG, "onSuccess: " + resultsContainer);
        }, throwable -> {
            Log.e(TAG, "onError: ", throwable);
        });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

}
