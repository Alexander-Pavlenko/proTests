package ua.nure.pavlenko.SummaryTask4.model.dao.impl;

import ua.nure.pavlenko.SummaryTask4.exception.Massages;
import ua.nure.pavlenko.SummaryTask4.exception.ObjectNotExist;
import ua.nure.pavlenko.SummaryTask4.model.dao.sql.Fields;
import ua.nure.pavlenko.SummaryTask4.model.dto.FilterDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Test;
import ua.nure.pavlenko.SummaryTask4.model.entity.TypeOfTest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 24.05.2017.
 */
public class TestDaoImpl extends CRUDDAO<Test> {

    private final String FIND_BY_PARAMETER = "SELECT * FROM test where `name` like ? " +
            "and `subject_id` like ? " +
            "and (`type` like ? or `type` like ? or `type` like ?) " +
            "and `isDelete` = 0";
    private static final String INSERT = "INSERT INTO test (name, subject_id, description, type, icon, test_time) VALUES (?,?,?,?,?,?)";

    public TestDaoImpl(Class<Test> type) {
        super(type);
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Test entity) throws SQLException, IOException {
        return null;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Test entity) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        int k = 1;
        preparedStatement.setString(k++, entity.getName());

        preparedStatement.setInt(k++, entity.getSubject_id());
        preparedStatement.setString(k++, entity.getDescription());
        preparedStatement.setString(k++, entity.getTypeOfTest().getType());
        preparedStatement.setString(k++, entity.getPathToIcon());
        preparedStatement.setInt(k++, entity.getTimeTest());

        return preparedStatement;
    }

    @Override
    protected List<Test> readAll(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException {
        List<Test> result = new ArrayList<>();
        Test test;
        while (resultSet.next()) {
            if (resultSet.getInt(Fields.IS_DELETE) == 1)
                continue;

            test = new Test();

            test.setId(resultSet.getInt(Fields.ENTITY_ID));
            test.setSubject_id(resultSet.getInt(Fields.TEST_SUBJECT_ID));
            test.setName(resultSet.getString(Fields.TEST_NAME));
            test.setTimeTest(resultSet.getInt(Fields.TEST_TEST_TIME));
            test.setDescription(resultSet.getString(Fields.TEST_DESCRIPTION));
            test.setTypeOfTest(TypeOfTest.valueOf(((String) resultSet.getString(Fields.TEST_TYPE)).toUpperCase()));
            test.setPathToIcon(resultSet.getString(Fields.TEST_ICON));

            result.add(test);
        }
        return result;
    }

    public List<Test> findTestsByParameter(FilterDto filterDao) throws ObjectNotExist {
        List<Test> result = null;
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PARAMETER)) {
            int k = 1;
            preparedStatement.setString(k++, "%" + filterDao.getName() + "%");
            preparedStatement.setString(k++, filterDao.getSubject_id());
            preparedStatement.setString(k++, filterDao.getType_topic());
            preparedStatement.setString(k++, filterDao.getType_module());
            preparedStatement.setString(k++, filterDao.getType_course());
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
