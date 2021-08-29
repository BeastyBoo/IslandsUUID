package com.github.beastyboo.islandsuuid.island;

import java.util.Objects;
import java.util.UUID;

public class Island {

    private final UUID uuid;
    private final String description;

    public Island(UUID uuid, String description) {
        this.uuid = uuid;
        this.description = description;
    }

    public Island() {
        this(UUID.randomUUID(), "Default Description");
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Island island = (Island) o;
        return uuid.equals(island.uuid) && description.equals(island.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, description);
    }

    @Override
    public String toString() {
        return "Island{" +
                "uuid=" + uuid +
                ", description='" + description + '\'' +
                '}';
    }
}
