package ua.nure.pavlenko.SummaryTask4.controller.command.AdminCommands;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.controller.command.Command;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.UnsupportedException;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.AnswerServiceImpl;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexander on 30.05.2017.
 */
public class CreateQuest extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String forward = Path.COMMAND_ADMIN_OFFICE_TEST;
        String questionText = request.getParameter(Attribute.QUESTION);
        String code  = request.getParameter(Attribute.CODE);
        Integer testId  = Integer.valueOf(request.getParameter(Attribute.TEST_ID));
        String[] index = request.getParameterValues(Attribute.TRUTHFUL);
        List<Answer> listAnswer = AnswerServiceImpl.getInstance().returnAnswers(index, request);


        Question question = new Question();
        question.setTest_id(testId);
        question.setAnswers(listAnswer);
        question.setQuestion(questionText);
        question.setCode(code);

        try {
            QuestionServiceImpl.getInstance().save(question);
        } catch (AppException e) {
            e.printStackTrace();
        }
        return forward;



    }
}
