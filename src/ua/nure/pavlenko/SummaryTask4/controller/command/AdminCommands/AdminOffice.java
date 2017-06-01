package ua.nure.pavlenko.SummaryTask4.controller.command.AdminCommands;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.controller.command.Command;
import ua.nure.pavlenko.SummaryTask4.model.dto.QuestDto;
import ua.nure.pavlenko.SummaryTask4.model.dto.SubjectDto;
import ua.nure.pavlenko.SummaryTask4.model.dto.TestDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;
import ua.nure.pavlenko.SummaryTask4.model.entity.Test;
import ua.nure.pavlenko.SummaryTask4.model.entity.TypeOfTest;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.QuestionServiceImpl;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.SubjectServiceImpl;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexander on 30.05.2017.
 */
public class AdminOffice extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String forward = Path.ADMIN_OFFICE_TEST;
        List<SubjectDto> subjectDto = SubjectServiceImpl.getInstance().getAll();
        List<String> types = TypeOfTest.getAllType();
        List<Question> questions = QuestionServiceImpl.getInstance().getAll();
        List<TestDto> testDtos = TestServiceImpl.getInstance().getAll();
        List<TestDto> testDtoList = TestServiceImpl.getInstance().filterByType(testDtos, TypeOfTest.TOPIC);


        request.setAttribute(Attribute.TEST_LIST, testDtoList);
        request.setAttribute(Attribute.TESTS, testDtos);
        request.setAttribute(Attribute.QUESTIONS, questions);
        request.setAttribute(Attribute.TYPES, types);
        request.setAttribute(Attribute.SUBJECTS, subjectDto);

        return forward;
    }
}
