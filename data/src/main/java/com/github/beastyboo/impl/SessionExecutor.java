package com.github.beastyboo.impl;

import com.github.beastyboo.Transaction;
import com.mongodb.client.ClientSession;

final class SessionExecutor implements AutoCloseable{

    private final ClientSession session;
    private final Transaction transaction;

    SessionExecutor(ClientSession session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    public void executeSession() {
        session.startTransaction();
        try {
            transaction.run();
            session.commitTransaction();
        } catch (RuntimeException ex) {
            session.abortTransaction();
        }
    }

    @Override
    public void close() throws Exception {
        session.close();
    }
}
