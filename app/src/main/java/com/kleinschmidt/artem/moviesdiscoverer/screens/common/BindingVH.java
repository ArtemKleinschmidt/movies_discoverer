package com.kleinschmidt.artem.moviesdiscoverer.screens.common;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Artem Kleinschmidt on 27.09.2017.
 */

public abstract class BindingVH <E, T extends ViewDataBinding> extends RecyclerView.ViewHolder{
    private final ViewDataBinding binding;

    public BindingVH(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setItem(E item) {
        binding.setVariable(getVariableId(), item);
        binding.executePendingBindings();
    }

    protected abstract int getVariableId();

}
