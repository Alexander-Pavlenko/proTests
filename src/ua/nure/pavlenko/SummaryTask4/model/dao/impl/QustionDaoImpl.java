package ua.nure.pavlenko.SummaryTask4.model.dao.impl;

import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.Massages;
import ua.nure.pavlenko.SummaryTask4.exception.ObjectNotExist;
import ua.nure.pavlenko.SummaryTask4.model.dao.DaoFactory;
import ua.nure.pavlenko.SummaryTask4.model.dao.sql.Fields;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;
import ua.nure.pavlenko.SummaryTask4.model.entity.Test;
import ua.nure.pavlenko.SummaryTask4.model.entity.TypeOfTest;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.QuestionServiceImpl;

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
public class QustionDaoImpl extends CRUDDAO<Question> {
    private final String SELECT_BY_TEST_ID = "SELECT * FROM question WHERE test_id = ?";

    public QustionDaoImpl(Class<Question> type) {
        super(type);
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Question entity) throws SQLException, IOException {
        return null;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Question entity) throws SQLException, IOException {
        return null;
    }

    @Override
    protected List<Question> readAll(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException, AppException {
        List<Question> result = new ArrayList<>();
        Question question;
        while (resultSet.next()) {
            if (resultSet.getInt(Fields.IS_DELETE) == 1)
                continue;

            question = new Question();

            question.setId(resultSet.getInt(Fields.ENTITY_ID));
            question.setTest_id(resultSet.getInt(Fields.TEST_ID));
            question.setQuestion(resultSet.getString(Fields.QUESTION));
            question.setCode(resultSet.getString(Fields.QUESION_CODE));
            question.setAnswers(((AnswerDaoImpl)DaoFactory.getInstance().getAnswerDao()).getAnswersByQuestionId(question.getId()));

            result.add(question);
        }
        return result;
    }

    public List<Question> getQuestionsByTestId(Integer test_id) throws AppException {
        List<Question> result = null;
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TEST_ID)) {
            preparedStatement.setString(1, String.valueOf(test_id));

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
