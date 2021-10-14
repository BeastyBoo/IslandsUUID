package com.github.beastyboo.module;

import com.github.beastyboo.IslandsImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class IslandModule extends AbstractModule {

    private final IslandsImpl core;

    public IslandModule(IslandsImpl core) {
        this.core = core;
    }

    @Provides
    public IslandsImpl providedCore() {
        return core;
    }

}
