package ua.nure.pavlenko.SummaryTask4.model.dao.impl;

import ua.nure.pavlenko.SummaryTask4.model.dao.sql.Fields;
import ua.nure.pavlenko.SummaryTask4.model.entity.Subject;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 21.05.2017.
 */
public class SubjectDaoImpl extends CRUDDAO<Subject> {

    private static final String INSERT = "INSERT INTO subject (name, icon) VALUES (?,?)";


    public SubjectDaoImpl(Class<Subject> type) {
        super(type);
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Subject entity) throws SQLException, IOException {
        return null;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Subject entity) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        int k = 1;
        preparedStatement.setString(k++, entity.getName());
        preparedStatement.setString(k++, entity.getIcon());
        return preparedStatement;
    }

    @Override
    protected List<Subject> readAll(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException {
       List<Subject> subjects = new ArrayList<>();
       Subject subject;

       while (resultSet.next()){
           subject = createSubject(resultSet);
           subjects.add(subject);
       }
       return subjects;
    }

    /////////////////////////////////////////////////////////////////////////

    private Subject createSubject(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getInt(Fields.ENTITY_ID));
        subject.setIcon(resultSet.getString(Fields.SUBJECT_ICON));
        subject.setName(resultSet.getString(Fields.SUBJECT_NAME));
        return subject;
    }
}
