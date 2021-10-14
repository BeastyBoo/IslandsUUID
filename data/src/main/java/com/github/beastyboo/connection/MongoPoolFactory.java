package com.github.beastyboo.connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class MongoPoolFactory {

    private final Logger logger;
    private final String uri, database;

    public MongoPoolFactory(Logger logger, String uri, String database) {
        this.logger = logger;
        this.uri = uri;
        this.database = database;
    }

    public MongoPool startConnection() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.OFF);

        MongoClient client = null;
        MongoDatabase mongoDatabase = null;

        try {
            client = MongoClients.create(uri);
            mongoDatabase = client.getDatabase(database);
            logger.info("Connected to database: " + database);
            return new MongoPool(client, mongoDatabase);
        } catch (RuntimeException ignored) {
            logger.warning("MongoDB connection failed! Make sure to add your URI and DB name.");
            return null;
        }
    }
}
