package ua.nure.pavlenko.SummaryTask4.model.dao.impl;

import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.Massages;
import ua.nure.pavlenko.SummaryTask4.exception.ObjectNotExist;
import ua.nure.pavlenko.SummaryTask4.model.dao.sql.Fields;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;
import ua.nure.pavlenko.SummaryTask4.model.entity.Test;
import ua.nure.pavlenko.SummaryTask4.model.entity.TypeOfTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 27.05.2017.
 */
public class AnswerDaoImpl extends CRUDDAO<Answer> {

    private final String SELECT_BY_QUESTION_ID = "SELECT * FROM answer WHERE question_id = ?";

    public AnswerDaoImpl(Class<Answer> type) {
        super(type);
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Answer entity) throws SQLException, IOException {
        return null;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Answer entity) throws SQLException, IOException {
        return null;
    }

    @Override
    protected List<Answer> readAll(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException, AppException {
        List<Answer> result = new ArrayList<>();
        Answer answer;
        while (resultSet.next()) {
            if (resultSet.getInt(Fields.IS_DELETE) == 1)
                continue;

            answer = new Answer();

            answer.setId(resultSet.getInt(Fields.ENTITY_ID));
            answer.setQuestion_id(resultSet.getInt(Fields.QUESTION_ID));
            answer.setAnswer(resultSet.getString(Fields.ANSWER));
            answer.setTruthful(resultSet.getBoolean(Fields.TRUTHFUL));

            result.add(answer);
        }
        return result;
    }

    public List<Answer> getAnswersByQuestionId(Integer question_id) throws AppException {
        List<Answer> result = null;
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_QUESTION_ID)) {
            preparedStatement.setString(1, String.valueOf(question_id));

            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (result == null || result.size() == 0) {
            throw new ObjectNotExist(Massages.INFORM_ABOUT_NOT_FIND_RESULTS);
        }
        return result;
    }

}
