package com.kleinschmidt.artem.moviesdiscoverer.api.services;

import com.kleinschmidt.artem.moviesdiscoverer.pojo.ResultsContainer;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.detailed_video.DetailedVideo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */

public interface VideosService {

    @GET("discover/movie?sort_by=popularity.desc&include_adult=false&include_video=false")
    Single<ResultsContainer> getPopularMovies();

    @GET("movie/{id}")
    Single<DetailedVideo> getDetailedMovie(@Path("id") int movieId);

}
