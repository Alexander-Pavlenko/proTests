package ua.nure.pavlenko.SummaryTask4.model.service.impl;

import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;

import ua.nure.pavlenko.SummaryTask4.model.dao.DaoFactory;
import ua.nure.pavlenko.SummaryTask4.model.dao.api.Dao;

import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;

import ua.nure.pavlenko.SummaryTask4.model.service.api.Service;
import ua.nure.pavlenko.SummaryTask4.utils.mapper.BeanMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import java.util.List;


/**
 * Created by Alexander on 27.05.2017.
 */
public class AnswerServiceImpl implements Service<Answer> {

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






    public List<Answer> returnAnswers(String[] index, HttpServletRequest request) {
        List<Answer> list = new ArrayList<>();

        List<Integer> listIndex = returnAllAnswer(request);
        for(Integer indexParameter: listIndex){
            Answer answer = new Answer();
            answer.setAnswer(request.getParameter(Attribute.ANSWER + indexParameter));
            if(isCorrect(indexParameter, index)){
                answer.setTruthful(true);
            }
            list.add(answer);
        }
        return list;

    }

    private boolean isCorrect(Integer indexParametr, String[] index) {
        for(String s : index){
            if (String.valueOf(indexParametr).equals(s)) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> returnAllAnswer(HttpServletRequest request) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < Attribute.MAX_ANSWERS; i++){
            String s = request.getParameter(Attribute.ANSWER + i);
            if(!s.equals("")){
                list.add(i);
            }
        }
        return list;
    }

    public void save(Question entity) throws AppException {
        for(Answer answer : entity.getAnswers()){
            answer.setQuestion_id(entity.getId());
            save(answer);
        }
    }

    @Override
    public List<Answer> getAll() {
        return null;
    }

    @Override
    public Answer getById(Integer id) {
        return null;
    }

    @Override
    public Answer save(Answer entity) throws AppException {
        return answerDao.save(entity);
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public void update(Answer entity) {

    }
}
