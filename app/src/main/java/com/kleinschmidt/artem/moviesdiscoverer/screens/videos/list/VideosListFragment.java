package com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kleinschmidt.artem.moviesdiscoverer.R;
import com.kleinschmidt.artem.moviesdiscoverer.app.MoviesDiscovererApp;
import com.kleinschmidt.artem.moviesdiscoverer.databinding.FragmentVideosListBinding;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.Video;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */

public class VideosListFragment extends Fragment {

    private static final String TAG = "VideosListFragment";
    private FragmentVideosListBinding binding;
    private DetailedVideoScreenLauncher detailedVideoScreenLauncher;
    @Inject VideosAdapter adapter;
    @Inject List<Video> videoList;
    @Inject ViewModelProvider.Factory viewModelFactory;
    VideosListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        createViewModel();
    }

    private void injectDependencies() {
        DaggerVideoListComponent
                .factory()
                .create(MoviesDiscovererApp.appComponent(), getOnVideoItemClickListener())
                .inject(this);
    }

    private void createViewModel() {
        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(VideosListViewModel.class);
    }

    @NonNull
    private VideosAdapter.OnVideoItemClickLister getOnVideoItemClickListener() {
        return (id, title) -> {
            if (detailedVideoScreenLauncher != null) {
                detailedVideoScreenLauncher.launchDetailedVideoScreen(id, title);
            }
        };
    }

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
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.moviesRecycler.setAdapter(adapter);
        binding.moviesRecycler.setLayoutManager(manager);
    }

    private void connectToViewModel() {
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
