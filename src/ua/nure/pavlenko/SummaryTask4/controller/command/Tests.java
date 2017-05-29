package ua.nure.pavlenko.SummaryTask4.controller.command;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.exception.Massages;
import ua.nure.pavlenko.SummaryTask4.exception.ObjectNotExist;
import ua.nure.pavlenko.SummaryTask4.model.dto.SubjectDto;
import ua.nure.pavlenko.SummaryTask4.model.dto.TestDto;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.SubjectServiceImpl;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexander on 25.05.2017.
 */
public class Tests extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        SubjectDto subjectDto = null;
        String forward = Path.PAGE_TESTS;
        String subject_id;
        if ((subject_id = request.getParameter(Attribute.SUBJECT_ID)) != null) {
            session.setAttribute(Attribute.SUBJECT_ID, subject_id);
            List<SubjectDto> list = (List<SubjectDto>) session.getAttribute(Attribute.SUBJECTS);
            subjectDto = SubjectServiceImpl.getInstance().getByIdBySession(list, subject_id);
        }
        if(request.getParameter(Attribute.SEARCH)!=null){
            session.setAttribute(Attribute.SUBJECT_ID,null);
        }
        if(subjectDto == null){
            subjectDto = SubjectServiceImpl.getInstance().createDefaultValue();
        }
        try {
            if(request.getParameter(Attribute.SEARCH_BUTTON)!=null){
                if (request.getAttribute(Attribute.SEARCH).equals("")) {
                    return forward;
                }
            }
            request.setAttribute(Attribute.SUBJECT_ID,
                    session.getAttribute(Attribute.SUBJECT_ID));
            List<TestDto> testDtoList = TestServiceImpl.getInstance()
                    .findTestByParameter(request);
            request.setAttribute(Attribute.COUNT,testDtoList.size());
            request.setAttribute(Attribute.TESTS, testDtoList);
        } catch (ObjectNotExist objectNotExist) {
            request.setAttribute(Attribute.MASSAGE, objectNotExist.getMessage());
        }
        request.setAttribute(Attribute.SUBJECT,subjectDto);

        return forward;
    }

}
