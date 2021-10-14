package com.github.beastyboo;

import org.bson.conversions.Bson;

import java.util.Optional;
import java.util.concurrent.Future;

public interface MongoCenter {

    Future<?> runTransaction(Transaction transaction);

    Future<Optional<? extends DataObject>> getDataObject(Bson filter, Enum<? extends DataCollection> collection);
}
