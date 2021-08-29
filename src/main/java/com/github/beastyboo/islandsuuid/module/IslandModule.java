package com.github.beastyboo.islandsuuid.module;

import com.github.beastyboo.islandsuuid.IslandsImpl;
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
