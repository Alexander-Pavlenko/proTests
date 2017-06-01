package ua.nure.pavlenko.SummaryTask4.controller.command.AdminCommands;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.controller.command.Command;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.Massages;
import ua.nure.pavlenko.SummaryTask4.model.dto.SubjectDto;
import ua.nure.pavlenko.SummaryTask4.model.dto.TestDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Test;
import ua.nure.pavlenko.SummaryTask4.model.entity.TypeOfTest;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.SubjectServiceImpl;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander on 30.05.2017.
 */
public class CreateTest extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nameTest = request.getParameter(Attribute.NAME);
        String pathToIcon = request.getParameter(Attribute.ICON);
        String description = request.getParameter(Attribute.DESCRIPTION);
        Integer subject_id = Integer.valueOf(request.getParameter(Attribute.SUBJECT_ID));
        TypeOfTest test = TypeOfTest.valueOf((request.getParameter(Attribute.TYPE)).toUpperCase());
        Integer timeForTest = Integer.valueOf(request.getParameter(Attribute.TIME_FOR_TEST));

        TestDto testDto = new TestDto();
        testDto.setName(nameTest);
        testDto.setTimeTest(timeForTest);
        testDto.setDescription(description);
        testDto.setPathToIcon(pathToIcon);
        testDto.setSubject_id(subject_id);
        testDto.setTypeOfTest(test);

        String massage = Massages.CREATE_SUCCESS;
        String forward = Path.COMMAND_ADMIN_OFFICE_TEST;
        try {
            TestServiceImpl.getInstance().save(testDto);
        } catch (AppException e) {
            massage = e.getMessage();
        }


        request.setAttribute(Attribute.MASSAGE, massage);

        return forward;
    }
}
