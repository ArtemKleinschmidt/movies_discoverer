package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list;

import com.kleinschmidt.artem.moviesdiscoverer.BR;
import com.kleinschmidt.artem.moviesdiscoverer.databinding.ItemVideoBinding;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.Video;
import com.kleinschmidt.artem.moviesdiscoverer.screens.common.BindingVH;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

class VideoVH extends BindingVH<Video, ItemVideoBinding> {

    VideoVH(ItemVideoBinding binding, VideosAdapter.OnVideoItemClickLister onVideoItemClickLister) {
        super(binding);
        binding.setVideoClickListener(onVideoItemClickLister);
    }

    @Override
    protected int getVariableId() {
        return BR.video;
    }
}
