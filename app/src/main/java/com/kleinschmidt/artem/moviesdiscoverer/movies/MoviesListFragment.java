package com.kleinschmidt.artem.moviesdiscoverer.movies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kleinschmidt.artem.moviesdiscoverer.R;
import com.kleinschmidt.artem.moviesdiscoverer.api.ServiceGenerator;
import com.kleinschmidt.artem.moviesdiscoverer.databinding.FragmentMoviesListBinding;

/**
 * Created by Artem Kleinschmidt on 26.09.2017.
 */

public class MoviesListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMoviesListBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movies_list, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        //binding.setMarsdata(data);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    private void loadData() {
        //ServiceGenerator.getServiceGenerator().createService()
    }
}
