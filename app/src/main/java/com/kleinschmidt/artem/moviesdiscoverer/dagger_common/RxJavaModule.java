package com.kleinschmidt.artem.moviesdiscoverer.dagger_common;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@Module
@PerScreenScope
public class RxJavaModule {

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
