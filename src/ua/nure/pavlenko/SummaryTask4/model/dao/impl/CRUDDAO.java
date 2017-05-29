package ua.nure.pavlenko.SummaryTask4.model.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import ua.nure.pavlenko.SummaryTask4.database.dataSource.DataSource;

import lombok.Getter;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.UniqueException;
import ua.nure.pavlenko.SummaryTask4.model.dao.api.Dao;
import ua.nure.pavlenko.SummaryTask4.model.entity.Entity;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * Created by Alexander on 20.05.2017.
 */
public abstract class CRUDDAO<T extends Entity> implements Dao<T> {
    private Class<T> type;
    @Getter
    private DataSource dataSource;

    ///////////////////////////////////////////////////////////////////////////
    // SQL script
    ///////////////////////////////////////////////////////////////////////////

    private static final String SELECT_ALL = "SELECT * FROM %s";
    private static final String FIND_BY_ID = "SELECT * FROM %s WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?";

    ///////////////////////////////////////////////////////////////////////////

    public CRUDDAO(Class<T> type) {
        this.type = type;
        dataSource = DataSource.getInstance();
    }

    public List<T> getAll() {
        String sql = String.format(SELECT_ALL, type.getSimpleName());
        List result = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            result = readAll(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (AppException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T getById(Integer id) {
        String sql = String.format(FIND_BY_ID, type.getSimpleName());

        List result = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (AppException ax) {
            ax.printStackTrace();
        }
        return (T) result.get(0);
    }

    @Override
    public T save(T entity) throws AppException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = createInsertStatement(connection, entity);
        ) {
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }

        } catch (MySQLIntegrityConstraintViolationException e){
            throw new UniqueException(e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(T entity) throws AppException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = createUpdateStatement(connection, entity)) {
            preparedStatement.executeUpdate();
        } catch (Exception e){
          throw new AppException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer key) {
        String sql = String.format(DELETE_BY_ID, type.getSimpleName());
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract PreparedStatement createUpdateStatement(Connection connection, T entity) throws SQLException, IOException;

    protected abstract PreparedStatement createInsertStatement(Connection connection, T entity) throws SQLException, IOException;

    protected abstract List<T> readAll(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException, AppException;
}
