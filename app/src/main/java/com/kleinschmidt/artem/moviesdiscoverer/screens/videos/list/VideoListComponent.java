package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list;

import com.kleinschmidt.artem.moviesdiscoverer.app.AppComponent;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.ViewModelModule;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.CompositeDisposableModule;
import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.PerScreenScope;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@PerScreenScope
@Component(dependencies = {AppComponent.class}, modules = {VideosListModule.class, ViewModelModule.class,
        CompositeDisposableModule.class})
public interface VideoListComponent {

    void inject(VideosListFragment videosListFragment);

    @Component.Factory
    interface Factory {
        VideoListComponent create(AppComponent appComponent,
                                         @BindsInstance VideosAdapter.OnVideoItemClickLister onVideoItemClickLister);
    }

}
