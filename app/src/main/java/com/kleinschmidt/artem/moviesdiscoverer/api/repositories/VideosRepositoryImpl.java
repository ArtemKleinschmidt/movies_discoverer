package com.kleinschmidt.artem.moviesdiscoverer.api.repositories;

import com.kleinschmidt.artem.moviesdiscoverer.api.ServiceGenerator;
import com.kleinschmidt.artem.moviesdiscoverer.api.services.VideosService;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.ResultsContainer;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */

public class VideosRepositoryImpl implements VideosRepository {

    private VideosService videosService;

    @Override
    public Single<ResultsContainer> getPopularMovies() {
        return getOrCreateMoviesService()
                .getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private VideosService getOrCreateMoviesService() {
        if (videosService == null) {
            videosService = ServiceGenerator.getServiceGenerator().createService(VideosService.class);
        }
        return videosService;
    }

}
