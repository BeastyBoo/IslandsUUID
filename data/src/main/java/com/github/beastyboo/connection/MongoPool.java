package com.github.beastyboo.connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.Objects;

public final record MongoPool(MongoClient client, MongoDatabase database) {
    public MongoPool {
        Objects.requireNonNull(client);
        Objects.requireNonNull(database);
    }
}
