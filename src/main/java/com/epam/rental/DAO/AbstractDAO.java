package com.epam.rental.DAO;

import com.epam.rental.DAO.api.DAO;
import com.epam.rental.builder.Builder;
import com.epam.rental.builder.BuilderFactory;
import com.epam.rental.entities.Identifiable;
import com.epam.rental.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/*package*/ class AbstractDAO<T extends Identifiable> implements DAO<T> {
    private final static Logger log = LogManager.getLogger(AbstractDAO.class);
    private static final String SELECT_FROM = "Select * from ";
    private static final String WHERE_ID = "where id = ";
    private static final String END_QUERY = ";";
    private static final String DELETE_FROM = "delete from ";

    protected void executeUpdate(String query, String... params) {
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (OperationNotSupportedException e) {
            log.error(e.getMessage(), e);
        }
        try {
            //assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setString((i + 1), params[i]);
                }
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    protected List<T> executeQuery(String query, Builder<T> builder, String... params) {

        Connection connection = null;
        try {
            connection = getConnection();
        } catch (OperationNotSupportedException e) {
            log.error(e.getMessage(), e);
        }
        List<T> entities = new ArrayList<T>();
        try {
            //assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if (params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setString((i + 1), params[i]);
                }
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                entities.add(entity);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        //ConnectionPool.closeConnection(connection);
        return entities;
    }


    protected Optional<T> executeForSingleResult(String query, Builder<T> builder, String... params) {
        List<T> items = executeQuery(query, builder, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> getById(Long id) {
        List<T> entities = getAll();
        for (T entity : entities) {
            Long entityId = entity.getId();
            if (Objects.equals(entityId, id)) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    public List<T> getAll() {
        String table = null;
        try {
            table = getTableName();
        } catch (OperationNotSupportedException e) {
            log.error(e.getMessage(), e);
        }
        BuilderFactory builderFactory = new BuilderFactory();
        Builder<T> builder = builderFactory.getBuilder(table);
        String query = SELECT_FROM + table + END_QUERY;
        return executeQuery(query, builder);

    }

    @Override
    public void save(T item) throws DaoException {

    }

    @Override
    public void remove(Long id) {
        String table = null;
        try {
            table = getTableName();
        } catch (OperationNotSupportedException e) {
            log.error(e.getMessage(), e);
        }
        executeUpdate(DELETE_FROM + table + WHERE_ID + id);
    }

    protected Connection getConnection() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    protected String getTableName() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();

    }
}