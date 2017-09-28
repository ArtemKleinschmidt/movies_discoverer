package com.kleinschmidt.artem.moviesdiscoverer.api.repositories;

import com.kleinschmidt.artem.moviesdiscoverer.pojo.ResultsContainer;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.detailed_video.DetailedVideo;

import io.reactivex.Single;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */

public interface VideosRepository {

    Single<ResultsContainer> getPopularMovies();

    Single<DetailedVideo> getDetailedVideo(int id);

}
