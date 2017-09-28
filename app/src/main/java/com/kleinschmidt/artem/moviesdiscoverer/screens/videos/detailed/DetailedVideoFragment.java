package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.detailed;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kleinschmidt.artem.moviesdiscoverer.R;
import com.kleinschmidt.artem.moviesdiscoverer.databinding.FragmentDetailedVideoBinding;
import com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list.VideosListFragment;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

public class DetailedVideoFragment extends Fragment {
    private static final String TAG = "DetailedVideoFragment";
    public static final String VIDEO_ID = "VIDEO_ID";
    public static final String TITLE = "TITLE";
    private int videoId;
    private String title;
    private DetailedVideoViewModel viewModel;
    private FragmentDetailedVideoBinding binding;
    private VideosListFragment.DetailedVideoScreenLauncher detailedVideoScreenLauncher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detailed_video, container, false);
        setTitle();
        showProgressBar();
        connectToViewModel();
        return binding.getRoot();
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void setTitle() {
        binding.collapsingToolbar.setTitle(title);
    }

    private void connectToViewModel() {
        viewModel = ViewModelProviders.of(this).get(DetailedVideoViewModel.class);
        viewModel.getDetailedVideo(videoId).observe(this,
                detailedVideo -> {
                    binding.setDetailedVideo(detailedVideo);
                    binding.progressBar.setVisibility(View.GONE);
                    binding.dataFields.setVisibility(View.VISIBLE);
                });
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof VideosListFragment.DetailedVideoScreenLauncher) {
            detailedVideoScreenLauncher = (VideosListFragment.DetailedVideoScreenLauncher) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        detailedVideoScreenLauncher = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readArguments();

    }

    /**
     * @throws IllegalStateException if getArguments() returns null,
     * or if it contains no VIDEO_ID or no TITLE
     */
    private void readArguments() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            videoId = bundle.getInt(VIDEO_ID);
            title = bundle.getString(TITLE);
        }
        if (videoId == 0) {
            throw new IllegalStateException("VIDEO_ID is not set!");
        }
        if(title == null) {
            throw new IllegalStateException("TITLE is not set!");
        }
    }

}
