package ua.nure.pavlenko.SummaryTask4.model.service.impl;

import ua.nure.pavlenko.SummaryTask4.controller.command.ResultTest;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.UnsupportedException;
import ua.nure.pavlenko.SummaryTask4.model.dao.DaoFactory;
import ua.nure.pavlenko.SummaryTask4.model.dao.api.Dao;
import ua.nure.pavlenko.SummaryTask4.model.dao.impl.QustionDaoImpl;
import ua.nure.pavlenko.SummaryTask4.model.dao.impl.UserAnswerDaoImpl;
import ua.nure.pavlenko.SummaryTask4.model.dao.impl.UserResultDaoImpl;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;
import ua.nure.pavlenko.SummaryTask4.model.entity.UserResult;
import ua.nure.pavlenko.SummaryTask4.model.service.api.Service;

import java.util.List;

/**
 * Created by Alexander on 30.05.2017.
 */
public class UserResultServiceImpl implements Service<UserResult> {

    private static UserResultServiceImpl service;
    private Dao<UserResult> userResultDao;


    private UserResultServiceImpl() {
        userResultDao = DaoFactory.getInstance().getUserResultDao();

    }

    public static synchronized UserResultServiceImpl getInstance() {
        if (service == null) {
            service = new UserResultServiceImpl();
        }
        return service;
    }

    @Override
    public List<UserResult> getAll() {
        return null;
    }

    @Override
    public UserResult getById(Integer id) {
        return null;
    }

    @Override
    public UserResult save(UserResult entity) throws AppException {
         UserResult userResult = userResultDao.save(entity);
         for(Answer answer : userResult.getAnswers()) {
             ((UserAnswerDaoImpl) DaoFactory.getInstance().getUserAnswerDao()).save(answer,userResult);
         }
         return userResult;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public void update(UserResult entity) {

    }

    public List<UserResult> getUserResultByUserId(Integer user_id) throws AppException {
        List<UserResult> list = ((UserResultDaoImpl)userResultDao).getUserResultByUserId(user_id);
        return list;
    }
}
