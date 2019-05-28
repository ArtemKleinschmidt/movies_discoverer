package com.kleinschmidt.artem.moviesdiscoverer.dagger_common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.kleinschmidt.artem.moviesdiscoverer.screens.videos.detailed.DetailedVideoViewModel;
import com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list.VideosListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Artem Kleinschmidt on 28.05.19.
 */
@Module
public interface ViewModelModule {

    @Binds
    @PerScreenScope
    ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @PerScreenScope
    @IntoMap
    @ViewModelKey(klass = DetailedVideoViewModel.class)
    ViewModel detailedVideoViewModel(DetailedVideoViewModel viewModel);

    @Binds
    @PerScreenScope
    @IntoMap
    @ViewModelKey(klass = VideosListViewModel.class)
    ViewModel videosListViewModel(VideosListViewModel viewModel);

}
