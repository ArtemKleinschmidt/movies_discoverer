package com.kleinschmidt.artem.moviesdiscoverer.movies;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kleinschmidt.artem.moviesdiscoverer.R;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        setTitle();
        setFragmentIfNeeded(savedInstanceState);
    }

    private void setTitle() {
        CollapsingToolbarLayout toolbar = findViewById(R.id.collapsing_toolbar);
        toolbar.setTitle(getString(R.string.activity_movies_title));
    }

    private void setFragmentIfNeeded(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_list_root, new MoviesListFragment())
                    .commit();
        }
    }


}
