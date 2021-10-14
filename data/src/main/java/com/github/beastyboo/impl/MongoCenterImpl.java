package com.github.beastyboo.impl;

import com.github.beastyboo.DataCollection;
import com.github.beastyboo.DataObject;
import com.github.beastyboo.MongoCenter;
import com.github.beastyboo.Transaction;
import com.github.beastyboo.connection.MongoPool;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.Gson;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MongoCenterImpl implements MongoCenter {

    private final MongoPool mongoPool;
    private final ExecutorService service;
    private final ListeningExecutorService executor;

    public MongoCenterImpl(MongoPool mongoPool, int threads) {
        this.mongoPool = mongoPool;
        service = Executors.newFixedThreadPool(threads);
        executor = MoreExecutors.listeningDecorator(service);
    }

    @Override
    public Future<?> runTransaction(Transaction transaction) {
        return executor.submit(() -> {
            try(ClientSession session = mongoPool.client().startSession()) {
                new SessionExecutor(session, transaction).executeSession();
            }
        });
    }

    @Override
    public Future<Optional<? extends DataObject>> getDataObject(Bson filter, Enum<? extends DataCollection> collectionImpl) {
        return CompletableFuture.supplyAsync(() -> {
            try(ClientSession session = mongoPool.client().startSession()) {
                session.startTransaction();

                MongoCollection<Document> collection = mongoPool.database().getCollection(collectionImpl.name().toLowerCase());

                Document document = collection.find(filter).first();

                if(document == null) {
                    return Optional.empty();
                }

                //TODO: Abstract GSON deserialization needs to be added.
                return Optional.of(new Gson().fromJson(document.toJson(), DataObject.class));
            }
        });
    }
}
