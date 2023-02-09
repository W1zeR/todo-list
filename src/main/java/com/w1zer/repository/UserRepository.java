package com.w1zer.repository;

import com.w1zer.model.User;
import com.w1zer.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserRepository {
    private static final Logger logger = LogManager.getLogger(UserRepository.class);
    private final String SELECT_ONE = "SELECT * FROM AppUser WHERE login = ?;";
    private final String INSERT = "INSERT INTO AppUser (login, password) VALUES (?, ?);";

    public UserRepository() {
        try {
            JDBCUtils.registerDriver();
        } catch (SQLException e) {
            logger.error("Error while registering SQL driver for UserRepository", e);
        }
    }

    public User getOne(String login) {
        User result = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ONE)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result = getFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting one user", e);
        }
        return result;
    }

    private User getFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String login = resultSet.getString(2);
        String password = resultSet.getString(3);
        return new User(id, login, password);
    }

    public void insert(String login, String password) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while inserting user", e);
        }
    }

}
