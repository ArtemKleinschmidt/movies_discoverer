package com.kleinschmidt.artem.moviesdiscoverer.screens.videos;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kleinschmidt.artem.moviesdiscoverer.R;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.Video;

import java.util.List;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideoVH> {

    private final List<Video> videoList;


    VideosAdapter(@NonNull List<Video> videoList) {
        this.videoList = videoList;
    }

    @Override
    public VideoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding itemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_video, parent,false);
        return new VideoVH(itemBinding);
    }

    @Override
    public void onBindViewHolder(VideoVH holder, int position) {
        holder.setItem(videoList.get(position));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
