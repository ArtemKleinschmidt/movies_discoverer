package com.kleinschmidt.artem.moviesdiscoverer.screens.movies;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.kleinschmidt.artem.moviesdiscoverer.api.ServiceGenerator;
import com.kleinschmidt.artem.moviesdiscoverer.api.services.MoviesService;
import com.kleinschmidt.artem.moviesdiscoverer.pojo.ResultsContainer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

public class MoviesListViewModel extends ViewModel {

    private static final String TAG = "MoviesListViewModel";
    private final MutableLiveData<ResultsContainer> resultsContainerLiveData;

    public MoviesListViewModel() {
        this.resultsContainerLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ResultsContainer> getResultsContainerLiveData() {
        return resultsContainerLiveData;
    }

    public void loadData() {
        ServiceGenerator.getServiceGenerator().createService(MoviesService.class).getPopularMovies().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<ResultsContainer>() {
            @Override
            public void onSuccess(ResultsContainer resultsContainer) {
                resultsContainerLiveData.setValue(resultsContainer);
                Log.d(TAG, "onSuccess: " + resultsContainer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
            }
        });
    }

}
