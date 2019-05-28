package com.kleinschmidt.artem.moviesdiscoverer.screens.common;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

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
