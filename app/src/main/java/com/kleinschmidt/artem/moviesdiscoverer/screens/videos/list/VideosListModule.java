package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list;

import android.arch.lifecycle.ViewModelProviders;

import com.kleinschmidt.artem.moviesdiscoverer.api.repositories.VideosRepository;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.PerScreenScope;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.Video;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@Module
public class VideosListModule {

    @Provides
    VideosListViewModel provideVideosListViewModelModule(VideosListFragment fragment, VideosRepository videosRepository, CompositeDisposable compositeDisposable) {
        VideosListViewModel viewModel = ViewModelProviders.of(fragment).get(VideosListViewModel.class);
        viewModel.setVideosRepository(videosRepository);
        viewModel.setCompositeDisposable(compositeDisposable);
        return viewModel;
    }

    @Provides
    @PerScreenScope
    List<Video> provideVideosList() {
        return new ArrayList<>();
    }

    @Provides
    VideosAdapter provideVideosAdapter(List<Video> videoList, VideosAdapter.OnVideoItemClickLister onVideoItemClickLister) {
        return new VideosAdapter(videoList, onVideoItemClickLister);
    }

}
