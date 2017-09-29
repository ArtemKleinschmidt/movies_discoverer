package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.detailed;

import com.kleinschmidt.artem.moviesdiscoverer.app.AppComponent;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.CompositeDisposableModule;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.PerScreenScope;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@PerScreenScope
@Component(dependencies = {AppComponent.class}, modules = {DetailedVideoModule.class, CompositeDisposableModule.class})
public interface DetailedVideoComponent {

    void inject(DetailedVideoFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance Builder fragment(DetailedVideoFragment fragment);
        Builder appComponent(AppComponent appComponent);
        DetailedVideoComponent build();
    }

}
