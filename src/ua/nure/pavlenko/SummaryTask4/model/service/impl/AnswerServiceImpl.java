package ua.nure.pavlenko.SummaryTask4.model.service.impl;

import ua.nure.pavlenko.SummaryTask4.exception.UnsupportedException;
import ua.nure.pavlenko.SummaryTask4.model.dao.DaoFactory;
import ua.nure.pavlenko.SummaryTask4.model.dao.api.Dao;
import ua.nure.pavlenko.SummaryTask4.model.dao.impl.AnswerDaoImpl;
import ua.nure.pavlenko.SummaryTask4.model.dto.AnswerDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Subject;
import ua.nure.pavlenko.SummaryTask4.model.service.api.Service;
import ua.nure.pavlenko.SummaryTask4.utils.mapper.BeanMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 27.05.2017.
 */
public class AnswerServiceImpl implements Service<AnswerDto> {

    private static AnswerServiceImpl answerService;
    private Dao<Answer> answerDao;
    private BeanMapper beanMapper;

    private AnswerServiceImpl() {
        answerDao = DaoFactory.getInstance().getAnswerDao();
        beanMapper = BeanMapper.getInstance();
    }

    public static synchronized AnswerServiceImpl getInstance() {
        if (answerService == null) {
            answerService = new AnswerServiceImpl();
        }
        return answerService;
    }

    @Override
    public List<AnswerDto> getAll() {
        return null;
    }

    @Override
    public AnswerDto getById(Integer id) {
        return null;
    }

    @Override
    public AnswerDto save(AnswerDto entity) throws UnsupportedException {
        return null;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public void update(AnswerDto entity) {

    }

    public List<Answer> getAnswerByQuestionId(Integer id) {
        return null;
    }
}
