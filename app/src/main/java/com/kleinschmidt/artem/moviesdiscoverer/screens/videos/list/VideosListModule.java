package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list;

import com.kleinschmidt.artem.moviesdiscoverer.dagger_common.PerScreenScope;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.Video;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@Module
public class VideosListModule {

    @Provides
    @PerScreenScope
    List<Video> provideVideosList() {
        return new ArrayList<>();
    }

    @Provides
    @PerScreenScope
    VideosAdapter provideVideosAdapter(List<Video> videoList, VideosAdapter.OnVideoItemClickLister onVideoItemClickLister) {
        return new VideosAdapter(videoList, onVideoItemClickLister);
    }

}
