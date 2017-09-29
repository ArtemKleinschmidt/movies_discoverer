package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list;

import com.kleinschmidt.artem.moviesdiscoverer.app.AppComponent;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.PerScreenScope;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.RxJavaModule;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@PerScreenScope
@Component(dependencies = {AppComponent.class}, modules = {VideosListModule.class, RxJavaModule.class})
public interface VideoListComponent {

    void inject(VideosListFragment videosListFragment);

    @Component.Builder
    interface Builder {
        @BindsInstance Builder fragment(VideosListFragment fragment);
        @BindsInstance Builder onVideoItemClickLister(VideosAdapter.OnVideoItemClickLister onVideoItemClickLister);
        Builder appComponent(AppComponent appComponent);
        VideoListComponent build();
    }

}
