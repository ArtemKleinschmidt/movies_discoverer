package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.detailed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.VideosRepository;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.detailed_video.DetailedVideo;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Artem Kleinschmidt on 28.09.2017.
 */

public final class DetailedVideoViewModel extends ViewModel {
    private static final String TAG = "DetailedVideoViewModel";
    private MutableLiveData<DetailedVideo> detailedVideoLiveData;
    private VideosRepository videosRepository;
    private CompositeDisposable compositeDisposable;

    LiveData<DetailedVideo> getDetailedVideo(int id) {
        if (detailedVideoLiveData == null) {
            detailedVideoLiveData = new MutableLiveData<>();
            loadDetailedVideo(id);
        }
        return detailedVideoLiveData;
    }

    private void loadDetailedVideo(int id) {
        Disposable disposable = videosRepository
                .getDetailedVideo(id)
                .subscribe(detailedVideo -> {
                    detailedVideoLiveData.setValue(detailedVideo);
                    Log.d(TAG, "getDetailedVideo: " + detailedVideo);
                }, throwable -> {
                    Log.e(TAG, "getDetailedVideo: ", throwable);
                });
        compositeDisposable.add(disposable);
    }

    public void setVideosRepository(VideosRepository videosRepository) {
        this.videosRepository = videosRepository;
    }

    public void setCompositeDisposable(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

}
