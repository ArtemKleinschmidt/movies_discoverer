package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kleinschmidt.artem.moviesdiscoverer.R;
import com.kleinschmidt.artem.moviesdiscoverer.databinding.FragmentVideosListBinding;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */

public class VideosListFragment extends Fragment {

    private static final String TAG = "VideosListFragment";
    private FragmentVideosListBinding binding;
    private List<Video> videoList;
    private VideosAdapter adapter;
    private VideosListViewModel viewModel;
    private DetailedVideoScreenLauncher detailedVideoScreenLauncher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_videos_list, container, false);
        setTitle();
        configureRecycler();
        showProgressBar();
        connectToViewModel();
        return binding.getRoot();
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetailedVideoScreenLauncher) {
            detailedVideoScreenLauncher = (DetailedVideoScreenLauncher) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        detailedVideoScreenLauncher = null;
    }

    private void setTitle() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.popular_movies);
    }

    private void configureRecycler() {
        if (adapter == null) {
            adapter = new VideosAdapter(getOrCreateVideoList(), (id, title) -> {
                if (detailedVideoScreenLauncher != null) {
                    detailedVideoScreenLauncher.launchDetailedVideoScreen(id, title);
                }
            });
        }
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.moviesRecycler.setAdapter(adapter);
        binding.moviesRecycler.setLayoutManager(manager);
    }

    private List<Video> getOrCreateVideoList() {
        if (videoList == null) {
            videoList = new ArrayList<>();
        }
        return videoList;
    }

    private void connectToViewModel() {
        viewModel = ViewModelProviders.of(this).get(VideosListViewModel.class);
        viewModel.getPopularVideos().observe(this, resultsContainer -> {
            Log.d(TAG, "results are received: resultsContainer " + resultsContainer);
            binding.progressBar.setVisibility(View.GONE);
            videoList.addAll(resultsContainer.getVideos());
            adapter.notifyDataSetChanged();
        });
    }

    public interface DetailedVideoScreenLauncher {
        void launchDetailedVideoScreen(int id, String title);
    }

}
