package ua.nure.pavlenko.SummaryTask4.model.dao.impl;


import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.Massages;
import ua.nure.pavlenko.SummaryTask4.exception.ObjectNotExist;
import ua.nure.pavlenko.SummaryTask4.model.dao.sql.Fields;
import ua.nure.pavlenko.SummaryTask4.model.dto.TestDto;

import ua.nure.pavlenko.SummaryTask4.model.entity.TypeOfTest;
import ua.nure.pavlenko.SummaryTask4.model.entity.UserResult;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.TestServiceImpl;

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 30.05.2017.
 */
public class UserResultDaoImpl extends CRUDDAO<UserResult> {
    private static final String INSERT = "INSERT INTO user_resultat (user_id, result_field, test_id, date_start, date_finish) VALUES (?,?,?,?,?)";
    private final String SELECT_BY_USER_ID = "SELECT * FROM user_resultat WHERE user_id = ?";
    public UserResultDaoImpl(Class<UserResult> type) {
        super(type);
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, UserResult entity) throws SQLException, IOException {
        return null;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, UserResult entity) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        int k = 1;
        preparedStatement.setInt(k++, entity.getUserDto().getId());
        preparedStatement.setInt(k++, entity.getResult());
        preparedStatement.setInt(k++, entity.getTestDto().getId());

        preparedStatement.setTimestamp(k++, Timestamp.valueOf(entity.getDateStart()));
        preparedStatement.setTimestamp(k++, Timestamp.valueOf(entity.getDateFinish()));

        return preparedStatement;
    }

    @Override
    protected List<UserResult> readAll(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException, AppException {
        List<UserResult> result = new ArrayList<>();
        UserResult userResult;
        while (resultSet.next()) {
            if (resultSet.getInt(Fields.IS_DELETE) == 1)
                continue;

            userResult = new UserResult();

            userResult.setId(resultSet.getInt(Fields.ENTITY_ID));
            TestDto testDto = TestServiceImpl.getInstance().getById(resultSet.getInt(Fields.TEST_ID));
            userResult.setTestDto(testDto);
            userResult.setResult(resultSet.getInt(Fields.RESULT_FIELD));


            result.add(userResult);
        }
        return result;
    }

    public List<UserResult> getUserResultByUserId(Integer user_id) throws ObjectNotExist {
        List<UserResult> result = null;
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_ID)) {
            preparedStatement.setString(1, String.valueOf(user_id));

            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (AppException e) {
            throw new ObjectNotExist(Massages.RESULTS_DO_NOT_FIND);
        }
        if (result == null || result.size() == 0) {
            throw new ObjectNotExist(Massages.INFORM_ABOUT_NOT_FIND_RESULTS);
        }
        return result;
    }


}
