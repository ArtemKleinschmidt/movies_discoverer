package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kleinschmidt.artem.moviesdiscoverer.R;
import com.kleinschmidt.artem.moviesdiscoverer.databinding.ItemVideoBinding;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.Video;

import java.util.List;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideoVH> {

    private final List<Video> videoList;
    private final OnVideoItemClickLister onVideoItemClickLister;


    VideosAdapter(@NonNull List<Video> videoList, OnVideoItemClickLister onVideoItemClickLister) {
        this.videoList = videoList;
        this.onVideoItemClickLister = onVideoItemClickLister;
    }

    @Override
    public VideoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemVideoBinding itemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_video, parent,false);
        return new VideoVH(itemBinding, onVideoItemClickLister);
    }

    @Override
    public void onBindViewHolder(VideoVH holder, int position) {
        holder.setItem(videoList.get(position));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public interface OnVideoItemClickLister {
        void onVideoClicked(int id, String title);
    }

}
