package ua.nure.pavlenko.SummaryTask4.model.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.UniqueException;
import ua.nure.pavlenko.SummaryTask4.model.dto.UserDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.User;
import ua.nure.pavlenko.SummaryTask4.model.entity.UserResult;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * Created by Alexander on 30.05.2017.
 */
public class UserAnswerDaoImpl extends CRUDDAO<Answer> {
    private static final String INSERT = "INSERT INTO user_answer (answer_id, user_resultat_id) VALUES (?,?)";

    public UserAnswerDaoImpl(Class<Answer> type) {
        super(type);
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Answer entity) throws SQLException, IOException {
        return null;
    }


    protected PreparedStatement createInsertStatement(Connection connection, Answer entity, UserResult userResult) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        int k = 1;
        preparedStatement.setInt(k++, entity.getId());
        preparedStatement.setInt(k++, userResult.getId());

        return preparedStatement;
    }
    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Answer entity) throws SQLException, IOException {
        return null;
    }

    @Override
    protected List<Answer> readAll(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException, AppException {
        return null;
    }

    public Answer save(Answer entity, UserResult userResult) throws AppException {
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement preparedStatement = createInsertStatement(connection, entity, userResult)) {
            preparedStatement.executeUpdate();
        } catch (MySQLIntegrityConstraintViolationException e){
            throw new UniqueException(e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
