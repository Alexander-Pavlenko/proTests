package ua.nure.pavlenko.SummaryTask4.controller.command;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.model.dto.QuestionAnswerDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;
import ua.nure.pavlenko.SummaryTask4.utils.CommandUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 28.05.2017.
 */
public class CollectorAnswer extends Command {
    private List<Answer> listAnswer;
    private List<QuestionAnswerDto> list;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String forward = Path.PAGE_TESTING;
        HttpSession session = request.getSession();
        Integer index = Integer.valueOf(request.getParameter(Attribute.QUESTION_INDEX));


        list = (List<QuestionAnswerDto>) session.getAttribute(Attribute.TEST_LIST);

        if (request.getParameter(Attribute.ANSWER) != null) {

            String[] arrayAnswer = request.getParameterValues(Attribute.ANSWER);
            listAnswer = new ArrayList<>();
            for (String id : arrayAnswer) {
                listAnswer.add(CommandUtil.returnElementById(list.get(index).getQuestion().getAnswers(), id));
            }
            list.get(index).setAnswers(listAnswer);
            index++;
        }


        request.setAttribute(Attribute.QUESTION_INDEX, CommandUtil.check(index, list));
        String finished = null;
        if (request.getAttribute(Attribute.QUESTION_INDEX).equals(-1)) {
            index = 0;
            finished = "finished";
        }
        request.setAttribute(Attribute.FINISHED, finished);
        request.setAttribute(Attribute.QUESTION_INDEX, index);
        request.setAttribute(Attribute.QUESTION, list.get(index));
        request.setAttribute(Attribute.BOX, CommandUtil.chooseBox(list.get(index).getQuestion().getAnswers()));
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
            private final StringWriter sw = new StringWriter();

            @Override
            public PrintWriter getWriter() throws IOException {
                return new PrintWriter(sw);
            }

            @Override
            public String toString() {
                return sw.toString();
            }
        };
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher(Path.QUESTION).include(request, responseWrapper);
        String content = responseWrapper.toString();
        out.print(content);
        return null;
    }


}
