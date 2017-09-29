package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.detailed;

import android.arch.lifecycle.ViewModelProviders;

import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.VideosRepository;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@Module
public class DetailedVideoModule {

    @Provides
    DetailedVideoViewModel provideVideosListViewModelModule(DetailedVideoFragment fragment, VideosRepository videosRepository, CompositeDisposable compositeDisposable) {
        DetailedVideoViewModel viewModel = ViewModelProviders.of(fragment).get(DetailedVideoViewModel.class);
        viewModel.setVideosRepository(videosRepository);
        viewModel.setCompositeDisposable(compositeDisposable);
        return viewModel;
    }

}
