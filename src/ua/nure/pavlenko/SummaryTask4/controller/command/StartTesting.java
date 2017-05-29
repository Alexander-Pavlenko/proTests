package ua.nure.pavlenko.SummaryTask4.controller.command;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.model.dto.QuestDto;
import ua.nure.pavlenko.SummaryTask4.model.dto.TestDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;
import ua.nure.pavlenko.SummaryTask4.model.dto.QuestionAnswerDto;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.QuestionServiceImpl;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 27.05.2017.
 */
public class StartTesting extends Command {
    private List<QuestionAnswerDto> list;
    private List<QuestDto> questDtos;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String forward = Path.COMMAND_TESTING;
        if(request.getParameter(Attribute.TEST_ID) == null){
            return Path.PAGE_ERROR_PAGE;
        }
        if (session.getAttribute(Attribute.TEST_LIST) == null) {
            list = new ArrayList<>();
            String test_id = (String) request.getParameter(Attribute.TEST_ID);
            try {
                List<Question> questions = QuestionServiceImpl.getInstance()
                        .getQuestionByIdTest(Integer.parseInt(test_id));
                addAnswer(questions);
                TestDto testDto = TestServiceImpl.getInstance().
                        getById(Integer.parseInt(test_id));
                session.setAttribute(Attribute.TEST_LIST, list);
                session.setAttribute(Attribute.QUESTION_LIST, questDtos);
                session.setAttribute(Attribute.TEST_ID, test_id);
                session.setAttribute(Attribute.TEST, testDto);
            } catch (AppException e) {
                forward = Path.COMMAND_RESULT;
                request.setAttribute(Attribute.FINISHED, Attribute.TRUE);

            }
        }
        request.setAttribute(Attribute.TEST_ID,
                session.getAttribute(Attribute.TEST_ID));
        return forward;
    }

    private void addAnswer(List<Question> list) {
        questDtos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
           this.list.add(new QuestionAnswerDto(list.get(i), new ArrayList<Answer>()));
           questDtos.add(new QuestDto(list.get(i).getId(), i));
        }
    }


}
