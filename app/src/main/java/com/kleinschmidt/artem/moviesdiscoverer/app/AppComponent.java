package com.kleinschmidt.artem.moviesdiscoverer.app;

import com.kleinschmidt.artem.moviesdiscoverer.api.ServicesModule;
import com.kleinschmidt.artem.moviesdiscoverer.api.VideosRepositoryModule;
import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.VideosRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@Singleton
@Component(modules = {VideosRepositoryModule.class, ServicesModule.class})
public interface AppComponent {

    VideosRepository getVideosRepository();

}
