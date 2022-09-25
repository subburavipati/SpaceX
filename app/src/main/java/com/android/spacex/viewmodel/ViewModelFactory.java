package com.android.spacex.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.android.spacex.api.source.remote.SpaceRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private SpaceRepository spaceRepository;
    public ViewModelFactory(Application application,
                     SpaceRepository spaceRepository) {
        this.application = application;
        this.spaceRepository = spaceRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SpaceViewModel(application, spaceRepository);
    }
}
