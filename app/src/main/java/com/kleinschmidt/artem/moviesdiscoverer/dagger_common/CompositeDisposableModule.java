package com.kleinschmidt.artem.moviesdiscoverer.dagger_common;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@Module
public class CompositeDisposableModule {

    @Provides
    @PerScreenScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
