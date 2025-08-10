package com.shop.zenstore.Database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB_Connection {

    private static final String CONNECTION_URI = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "zenstore";

    private static MongoClient mClient = null;

    public static MongoClient getMongoClient() {
        if (mClient == null) {
            mClient = MongoClients.create(CONNECTION_URI);
        }
        return mClient;
    }

    public static MongoDatabase getDatabase() {
        return getMongoClient().getDatabase(DATABASE_NAME);
    }

    public static void close() {
        if (mClient != null) {
            mClient.close();
            mClient = null;
        }
    }

}
