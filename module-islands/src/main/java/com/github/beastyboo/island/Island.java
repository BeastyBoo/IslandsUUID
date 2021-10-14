package com.github.beastyboo.island;

import com.github.beastyboo.DataObject;

import java.util.Objects;
import java.util.UUID;

public final class Island implements DataObject {

    private final UUID uuid;
    private final String description;

    public Island(UUID uuid) {
        this.uuid = uuid;
        this.description = "Description yooo.";
    }

    public UUID getUuid() {
        return uuid;
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
