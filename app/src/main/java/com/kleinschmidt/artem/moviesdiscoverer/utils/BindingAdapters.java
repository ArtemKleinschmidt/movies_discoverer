package com.kleinschmidt.artem.moviesdiscoverer.utils;

import android.content.Context;
import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

public final class BindingAdapters {

    private BindingAdapters() {
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

}
