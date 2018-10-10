package com.epam.rental.DAO.database;


import com.epam.rental.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.AbstractQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool implements AutoCloseable {

    private final static int NUMBER_OF_CONNECTION = 20;

    private final static Logger log = LogManager.getLogger(ConnectionPool.class);
    private static Lock entityLock = new ReentrantLock();
    private static Lock poolLock = new ReentrantLock();
    private Semaphore semaphore;
    private static ConnectionPool entity;
    private static AbstractQueue<Connection> connectionPool = new ConcurrentLinkedQueue<>();


    private ConnectionPool() {
        semaphore = new Semaphore(NUMBER_OF_CONNECTION);
        Handler handler = new Handler();
        for (int i = 0; i < NUMBER_OF_CONNECTION; i++) {
            try {
                Connection connection = handler.getConnection();
                connectionPool.add(connection);
            } catch (SQLException e) {
                log.error("Can't create connection to database.");
                throw new ConnectionPoolException("Error to create connection.", e);

            }
        }
    }

    public static ConnectionPool getEntity() {
        if (entity == null) {
            try {
                entityLock.lock();
                if (entity == null) {
                    entity = new ConnectionPool();
                }
            } finally {
                entityLock.unlock();
            }
        }
        return entity;
    }

    public Connection getConnection() {
        try {
            semaphore.acquire();
            poolLock.lock();
            return connectionPool.poll();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error to get connection.", e);
        } finally {
            poolLock.unlock();
        }
    }

    public void closeConnection(Connection connection) {
        poolLock.lock();
        connectionPool.offer(connection);
        poolLock.unlock();
        semaphore.release();
    }

    @Override
    public void close() throws Exception {
        connectionPool.forEach(connection -> {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionPoolException("Error to close connection.", e);
            }
        });
    }
}
