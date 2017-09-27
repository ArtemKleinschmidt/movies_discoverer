package com.kleinschmidt.artem.moviesdiscoverer.api.repositories;

import com.kleinschmidt.artem.moviesdiscoverer.api.ServiceGenerator;
import com.kleinschmidt.artem.moviesdiscoverer.api.services.MoviesService;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.ResultsContainer;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */

public class MoviesRepositoryImpl implements MoviesRepository {

    private MoviesService moviesService;

    @Override
    public Single<ResultsContainer> getPopularMovies() {
        return getOrCreateMoviesService()
                .getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private MoviesService getOrCreateMoviesService() {
        if (moviesService == null) {
            moviesService = ServiceGenerator.getServiceGenerator().createService(MoviesService.class);
        }
        return moviesService;
    }

}
