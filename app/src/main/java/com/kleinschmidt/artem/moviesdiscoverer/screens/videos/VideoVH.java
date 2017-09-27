package com.kleinschmidt.artem.moviesdiscoverer.screens.videos;

import android.databinding.ViewDataBinding;

import com.kleinschmidt.artem.moviesdiscoverer.BR;
import com.kleinschmidt.artem.moviesdiscoverer.screens.common.BindingVH;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

class VideoVH extends BindingVH {

    VideoVH(ViewDataBinding binding) {
        super(binding);
    }

    @Override
    protected int getVariableId() {
        return BR.video;
    }
}
