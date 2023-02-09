package com.w1zer.repository;

import com.w1zer.model.TODO;
import com.w1zer.model.TODOStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.w1zer.utils.JDBCUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class TODORepository {
    private static final Logger logger = LogManager.getLogger(TODORepository.class);
    private final String SELECT_ONE = "SELECT * FROM TODO WHERE id = ?;";
    private final String SELECT_ALL = "SELECT * FROM TODO WHERE userId = ?;";
    private final String INSERT =
            "INSERT INTO TODO (comment, created, shouldBeDoneBefore, userId, status) VALUES (?, ?, ?, ?, ?);";
    private final String UPDATE = "UPDATE TODO SET comment = ?, shouldBeDoneBefore = ?, status = ? WHERE id = ?;";
    private final String DELETE = "DELETE FROM TODO WHERE id = ?;";
    private final String UPDATE_STATUS_TO_OVERDUE = "UPDATE TODO SET status = 1 WHERE id = ?;";

    public TODORepository() {
        try {
            JDBCUtils.registerDriver();
        } catch (SQLException e) {
            logger.error("Error while registering SQL driver for TODORepository", e);
        }
    }

    public TODO getOne(long todoId) {
        TODO result = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ONE)) {
            statement.setLong(1, todoId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result = getFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting one TODO", e);
        }
        return result;
    }

    public List<TODO> getAll(long userId) {
        List<TODO> result = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(getFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting all TODOs", e);
        }
        return result;
    }

    private TODO getFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String comment = resultSet.getString(2);
        Date created = resultSet.getDate(3);
        Date shouldBeDoneBefore = resultSet.getDate(4);
        long userId = resultSet.getLong(5);
        TODOStatus status = TODOStatus.values()[resultSet.getByte(6)];
        return new TODO(id, comment, created, shouldBeDoneBefore, userId, status);
    }

    public void insert(TODO todo) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, todo.getComment());
            statement.setDate(2, (Date) todo.getCreated());
            statement.setDate(3, (Date) todo.getShouldBeDoneBefore());
            statement.setLong(4, todo.getUserId());
            statement.setInt(5, todo.getStatus().ordinal());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while inserting TODO", e);
        }
    }

    public void delete(long todoId) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, todoId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while deleting TODO", e);
        }
    }

    public void update(TODO todo) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, todo.getComment());
            statement.setDate(2, (Date) todo.getShouldBeDoneBefore());
            statement.setInt(3, todo.getStatus().ordinal());
            statement.setLong(4, todo.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while updating TODO", e);
        }
    }

    public void updateTODOWithWrongStatus(Collection<TODO> todos) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_TO_OVERDUE)) {
            for (TODO todo : todos) {
                statement.setLong(1, todo.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Error while updating TODO with wrong status", e);
        }
    }

}
