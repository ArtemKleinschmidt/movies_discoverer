package com.kleinschmidt.artem.moviesdiscoverer.api.repositories;

import com.kleinschmidt.artem.moviesdiscoverer.pojo.ResultsContainer;

import io.reactivex.Single;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */

public interface VideosRepository {

    Single<ResultsContainer> getPopularMovies();

}
