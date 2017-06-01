package ua.nure.pavlenko.SummaryTask4.controller.command;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.model.dto.QuestionAnswerDto;
import ua.nure.pavlenko.SummaryTask4.model.dto.TestDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.UserResult;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.UserResultServiceImpl;
import ua.nure.pavlenko.SummaryTask4.utils.CommandUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexander on 29.05.2017.
 */
public class ResultTest extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String forward = Path.PAGE_RESULT;
        TestDto testDto = (TestDto) session.getAttribute(Attribute.TEST);
        List<QuestionAnswerDto> listTest = (List<QuestionAnswerDto>) session.getAttribute(Attribute.TEST_LIST);
        Integer result = CommandUtil.returnResult(listTest);

        UserResult userResult = (UserResult) session.getAttribute(Attribute.USER_RESULT);
        userResult.setDateFinish(LocalDateTime.now());
        userResult.setAnswers(CommandUtil.getAnswerToList(listTest));
        userResult.setResult(result);
        userResult.setTestDto(testDto);
        try {
            UserResultServiceImpl.getInstance().save(userResult);
        } catch (AppException e) {
            e.printStackTrace();
        }

        session.removeAttribute(Attribute.TEST);
        session.removeAttribute(Attribute.USER_RESULT);
        session.removeAttribute(Attribute.TEST_LIST);
        session.removeAttribute(Attribute.QUESTION_LIST);
        session.removeAttribute(Attribute.TEST_ID);


        request.setAttribute(Attribute.TEST, testDto);
        request.setAttribute(Attribute.RESULT, result);



        return forward;
    }
}
