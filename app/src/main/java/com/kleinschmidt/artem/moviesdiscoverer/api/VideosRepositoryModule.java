package com.kleinschmidt.artem.moviesdiscoverer.api;

import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.VideosRepository;
import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.VideosRepositoryImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@Module
public abstract class VideosRepositoryModule {

    @Binds
    abstract VideosRepository bindVideosRepository(VideosRepositoryImpl videosRepository);

}
