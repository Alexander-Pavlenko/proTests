package ua.nure.pavlenko.SummaryTask4.model.service.impl;

import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.UnsupportedException;
import ua.nure.pavlenko.SummaryTask4.model.dao.DaoFactory;
import ua.nure.pavlenko.SummaryTask4.model.dao.api.Dao;
import ua.nure.pavlenko.SummaryTask4.model.dao.impl.QustionDaoImpl;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;
import ua.nure.pavlenko.SummaryTask4.model.entity.Subject;
import ua.nure.pavlenko.SummaryTask4.model.entity.Test;
import ua.nure.pavlenko.SummaryTask4.model.service.api.Service;
import ua.nure.pavlenko.SummaryTask4.utils.mapper.BeanMapper;

import java.util.List;

/**
 * Created by Alexander on 26.05.2017.
 */
public class QuestionServiceImpl implements Service<Question>{
    private  static QuestionServiceImpl questionService;
    private Dao<Question> questionDao;


    private QuestionServiceImpl(){
        questionDao = DaoFactory.getInstance().getQuestionDao();
    }

    public  static synchronized QuestionServiceImpl getInstance(){
        if(questionService == null){
            questionService = new QuestionServiceImpl();
        }
        return questionService;
    }
    @Override
    public List<Question> getAll() {
        return null;
    }

    @Override
    public Question getById(Integer id) {
        return null;
    }

    @Override
    public Question save(Question entity) throws UnsupportedException {
        return null;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public void update(Question entity) {

    }


    public List<Question> getQuestionByIdTest(Integer test_id) throws AppException {
        List<Question> list = ((QustionDaoImpl)questionDao).getQuestionsByTestId(test_id);
        return list;
    }
}
