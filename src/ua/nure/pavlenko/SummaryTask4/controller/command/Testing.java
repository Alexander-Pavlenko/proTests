package ua.nure.pavlenko.SummaryTask4.controller.command;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.model.dto.QuestDto;
import ua.nure.pavlenko.SummaryTask4.model.dto.QuestionAnswerDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;
import ua.nure.pavlenko.SummaryTask4.utils.CommandUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 26.05.2017.
 */
public class Testing extends Command {
    private List<QuestionAnswerDto> questions;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String forward = Path.PAGE_TESTING;

        HttpSession session = request.getSession();
        Integer index = (Integer) request.getAttribute(Attribute.QUESTION_INDEX);
        if(index == null){
            index = 0;
        }
        if (session.getAttribute(Attribute.TEST_LIST) == null ) {
            forward = Path.COMMAND_START_TESTING;
            return forward;
        }
        questions = (List<QuestionAnswerDto>) session.getAttribute(Attribute.TEST_LIST);
        if (request.getAttribute(Attribute.QUESTION_INDEX) == null) {
            request.setAttribute(Attribute.QUESTION_INDEX, CommandUtil.check(index, questions.get(index).getQuestion().getAnswers()));
        }

        if (request.getAttribute(Attribute.QUESTION_INDEX).equals(-1)) {
            return forward;
        }

        request.setAttribute(Attribute.QUESTION, questions.get((Integer) request.getAttribute(Attribute.QUESTION_INDEX)));

        request.setAttribute(Attribute.BOX, CommandUtil.chooseBox(questions.get(index).getAnswers()));
        return forward;

    }


}
