package ua.nure.pavlenko.SummaryTask4.controller.command;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.model.dto.TestDto;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander on 26.05.2017.
 */
public class Test extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TestDto testDto = TestServiceImpl.getInstance()
                .getById(Integer.valueOf(request.getParameter(Attribute.TEST_ID)));
        request.setAttribute(Attribute.TEST, testDto);
        return Path.PAGE_TEST;
    }
}
