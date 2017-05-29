package ua.nure.pavlenko.SummaryTask4.model.dao;


import ua.nure.pavlenko.SummaryTask4.database.PropertyHolder.PropertyHolder;
import lombok.Data;
import ua.nure.pavlenko.SummaryTask4.model.dao.api.Dao;
import ua.nure.pavlenko.SummaryTask4.model.dao.impl.*;
import ua.nure.pavlenko.SummaryTask4.model.entity.*;

/**
 * Created by Alexandr on 16.12.2016.
 */
@Data
public class DaoFactory {
    private static DaoFactory instance = null;

//
    private Dao<Subject> subjectDao;
    private Dao<User> userDao;
    private Dao<Test> testDao;
    private Dao<Question> questionDao;
    private Dao<Answer> answerDao;



    private DaoFactory(){
        loadDaos();
    }
    public static DaoFactory getInstance(){
        if(instance == null){
            instance = new DaoFactory();
        }
        return instance;
    }


    private void loadDaos() {
        if(PropertyHolder.getInstance().isInMemoryDB()){
        }else{
            subjectDao = new SubjectDaoImpl(Subject.class);
            userDao = new UserDaoImpl(User.class);
            testDao = new TestDaoImpl(Test.class);
            questionDao = new QustionDaoImpl(Question.class);
            answerDao = new AnswerDaoImpl(Answer.class);
        }
    }

}