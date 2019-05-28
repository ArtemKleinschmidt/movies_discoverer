package com.kleinschmidt.artem.moviesdiscoverer.screens.videos;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import com.kleinschmidt.artem.moviesdiscoverer.R;
import com.kleinschmidt.artem.moviesdiscoverer.screens.videos.detailed.DetailedVideoFragment;
import com.kleinschmidt.artem.moviesdiscoverer.screens.videos.list.VideosListFragment;

public class VideosActivity extends AppCompatActivity implements VideosListFragment.DetailedVideoScreenLauncher {

    private static final String TAG = "VideosActivity";
    private static final String BACKSTACK = "VideosBackstack";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        addFragmentIfNeeded(savedInstanceState);
    }

    private void addFragmentIfNeeded(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_root, new VideosListFragment())
                    .commit();
        }
    }

    @Override
    public void launchDetailedVideoScreen(int id, String title) {
        Fragment fragment = getDetailedVideoFragment(id, title);
        addFragmentToBackStack(fragment);
    }

    private Fragment getDetailedVideoFragment(int id, String title) {
        Fragment fragment = new DetailedVideoFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(DetailedVideoFragment.VIDEO_ID, id);
        bundle.putString(DetailedVideoFragment.TITLE, title);

        fragment.setArguments(bundle);
        return fragment;
    }

    private void addFragmentToBackStack(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_root, fragment)
                .addToBackStack(BACKSTACK)
                .commit();
    }

}
