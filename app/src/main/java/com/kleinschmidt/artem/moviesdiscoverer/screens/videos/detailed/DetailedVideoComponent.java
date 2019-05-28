package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.detailed;

import com.kleinschmidt.artem.moviesdiscoverer.app.AppComponent;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.ViewModelModule;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.CompositeDisposableModule;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.PerScreenScope;

import dagger.Component;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@PerScreenScope
@Component(dependencies = {AppComponent.class}, modules = {CompositeDisposableModule.class, ViewModelModule.class})
public interface DetailedVideoComponent {

    void inject(DetailedVideoFragment fragment);

}
