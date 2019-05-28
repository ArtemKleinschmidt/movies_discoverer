package com.kleinschmidt.artem.moviesdiscoverer.dagger_common;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Artem Kleinschmidt on 28.05.19.
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, ViewModel> viewModels;

    @NonNull
    public ViewModel create(@NonNull Class modelClass) {
        return this.viewModels.get(modelClass);
    }

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, ViewModel> viewModels) {
        super();
        this.viewModels = viewModels;
    }

}
