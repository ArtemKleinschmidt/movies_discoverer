package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.detailed;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kleinschmidt.artem.moviesdiscoverer.R;
import com.kleinschmidt.artem.moviesdiscoverer.app.MoviesDiscovererApp;
import com.kleinschmidt.artem.moviesdiscoverer.databinding.FragmentDetailedVideoBinding;

import javax.inject.Inject;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

public class DetailedVideoFragment extends Fragment {
    public static final String VIDEO_ID = "VIDEO_ID";
    public static final String TITLE = "TITLE";
    private int videoId;
    private String title;
    private FragmentDetailedVideoBinding binding;
    @Inject ViewModelProvider.Factory viewModelFactory;
    private DetailedVideoViewModel viewModel;

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
        viewModel.getDetailedVideo(videoId).observe(this,
                detailedVideo -> {
                    binding.setDetailedVideo(detailedVideo);
                    binding.progressBar.setVisibility(View.GONE);
                    binding.dataFields.setVisibility(View.VISIBLE);
                });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        createViewModel();
        readArguments();
    }

    private void injectDependencies() {
        DaggerDetailedVideoComponent
                .builder()
                .appComponent(MoviesDiscovererApp.appComponent())
                .build()
                .inject(this);
    }

    private void createViewModel() {
        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(DetailedVideoViewModel.class);
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
