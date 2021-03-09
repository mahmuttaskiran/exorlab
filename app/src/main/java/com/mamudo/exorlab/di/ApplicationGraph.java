package com.mamudo.exorlab.di;

import com.mamudo.exorlab.ui.main.MainActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component
public interface ApplicationGraph {
    void inject(MainActivity exampleStreamProvider);
}
