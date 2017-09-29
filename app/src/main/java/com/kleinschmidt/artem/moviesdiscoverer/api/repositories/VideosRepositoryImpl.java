package com.kleinschmidt.artem.moviesdiscoverer.api.repositories;

import com.kleinschmidt.artem.moviesdiscoverer.api.services.VideosService;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.ResultsContainer;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.detailed_video.DetailedVideo;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */
@Singleton
public class VideosRepositoryImpl implements VideosRepository {

    private VideosService videosService;

    @Inject
    public VideosRepositoryImpl(VideosService videosService) {
        this.videosService = videosService;
    }

    @Override
    public Single<ResultsContainer> getPopularMovies() {
        return videosService
                .getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<DetailedVideo> getDetailedVideo(int id) {
        return videosService
                .getDetailedMovie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
