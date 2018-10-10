package com.epam.rental.DAO;

import com.epam.rental.DAO.api.UserDAO;
import com.epam.rental.builder.Builder;
import com.epam.rental.builder.BuilderFactory;
import com.epam.rental.entities.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AdminDAO extends AbstractDAO<User> implements UserDAO {
    private static final String QUERY = "select * from administrator where Login = ? and Password = md5(?);";
    private static final String ADMINISTRATOR = "administrator";
    private Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        Optional<User> user;
        BuilderFactory builderFactory = new BuilderFactory();
        Builder<User> builder = builderFactory.getBuilder(ADMINISTRATOR);
        user = executeForSingleResult(QUERY, builder, login, password);
        return user;
    }

    @Override
    public Optional<User> getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public void save(User item) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public String getTableName(){
        return ADMINISTRATOR;
    }
}
