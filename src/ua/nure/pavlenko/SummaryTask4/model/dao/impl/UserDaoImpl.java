package ua.nure.pavlenko.SummaryTask4.model.dao.impl;


import ua.nure.pavlenko.SummaryTask4.exception.Massages;
import ua.nure.pavlenko.SummaryTask4.exception.ObjectNotExist;
import ua.nure.pavlenko.SummaryTask4.model.dao.sql.Fields;
import ua.nure.pavlenko.SummaryTask4.model.entity.Role;
import ua.nure.pavlenko.SummaryTask4.model.entity.User;

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 23.05.2017.
 */
public class UserDaoImpl extends CRUDDAO<User> {
    private static final String INSERT = "INSERT INTO user (login, password, first_Name, last_name, e_mail, role, date) VALUES (?,?,?,?,?,?,?)";
    private final String FIND_BY_LOGIN = "SELECT * FROM user WHERE login LIKE ?";
    private final String UPDATE = "UPDATE user SET login = ?, password = ?, first_Name = ?, last_name = ?, e_mail = ?, role = ?, date = ?, isBlocked = ? WHERE id = ?";

    ///////////////////////////////////////////////////////////////////////////////

    public UserDaoImpl(Class<User> type) {
        super(type);
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, User entity) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        int k = 1;
        preparedStatement.setString(k++, entity.getLogin());
        preparedStatement.setString(k++, entity.getPassword());
        preparedStatement.setString(k++, entity.getFirst_name());
        preparedStatement.setString(k++, entity.getLast_name());
        preparedStatement.setString(k++, entity.getE_mail());
        preparedStatement.setString(k++, entity.getRole().getType());
        preparedStatement.setTimestamp(k++, Timestamp.valueOf(entity.getDateRegestration()));
        preparedStatement.setInt(k++, entity.getStatus());
        preparedStatement.setInt(k++, entity.getId());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, User entity) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        int k = 1;
        preparedStatement.setString(k++, entity.getLogin());
        preparedStatement.setString(k++, entity.getPassword());
        preparedStatement.setString(k++, entity.getFirst_name());
        preparedStatement.setString(k++, entity.getLast_name());
        preparedStatement.setString(k++, entity.getE_mail());
        preparedStatement.setString(k++, entity.getRole().toString());
        preparedStatement.setTimestamp(k++, Timestamp.valueOf(entity.getDateRegestration()));

        return preparedStatement;
    }

    @Override
    protected List<User> readAll(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException {
        List<User> result = new ArrayList<>();
        User user;
        while (resultSet.next()) {
            if (resultSet.getInt(Fields.IS_DELETE) == 1)
                continue;

            user = new User();

            user.setId(resultSet.getInt(Fields.ENTITY_ID));
            user.setE_mail(resultSet.getString(Fields.USER_EMAIL));
            user.parseName(resultSet.getString(Fields.USER_FIRST_NAME));
            user.setLast_name(resultSet.getString(Fields.USER_LAST_NAME));
            user.setPhoneNumber(resultSet.getString(Fields.USER_NUMBER));
            user.setPhotoPath(resultSet.getString(Fields.USER_PHOTO));
            user.setDateRegestration(resultSet.getTimestamp(Fields.USER_DATE_REGISTRATION).toLocalDateTime());
            user.setPassword(resultSet.getString(Fields.USER_PASSWORD));
            user.setLogin(resultSet.getString(Fields.USER_LOGIN));
            user.setRole(Role.valueOf((resultSet.getString(Fields.USER_ROLE)).toUpperCase()));
            user.setIsActivated(user.setStatus(resultSet.getInt(Fields.USER_IS_ACTIVATED)));
            user.setIsBlocked(user.setStatus(resultSet.getInt(Fields.USER_IS_BLOCK)));

            result.add(user);
        }
        return result;
    }

    public User findUserByLogin(String login) throws ObjectNotExist {
        List<User> result = null;
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN);) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(result == null || result.size()==0){
            throw new ObjectNotExist(Massages.ERR_USER_DOESNT_EXIST);
        }
        return result.get(0);
    }


}
