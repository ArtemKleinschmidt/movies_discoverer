package com.kleinschmidt.artem.moviesdiscoverer.api;

import com.kleinschmidt.artem.moviesdiscoverer.api.services.VideosService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@Module
public class ServicesModule {

    @Provides
    VideosService provideVideosService(ServiceGenerator serviceGenerator) {
        return serviceGenerator.createService(VideosService.class);
    }

}
