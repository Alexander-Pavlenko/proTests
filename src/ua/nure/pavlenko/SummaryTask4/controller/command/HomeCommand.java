package ua.nure.pavlenko.SummaryTask4.controller.command;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.model.dto.SubjectDto;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexander on 21.05.2017.
 */
public class HomeCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String language = null;
        if((language = (String) session.getAttribute(Attribute.LANGUAGE)) == null){
            language = Attribute.LANGUAGE + Attribute.LANGENG;
            session.setAttribute(Attribute.LANGUAGE, language);
        }
        req.setAttribute(language, Attribute.ACTIVE);
        req.setAttribute(Attribute.BUTTON_ACTIVE_HOME, Attribute.ACTIVE);




        SubjectServiceImpl subjectService = SubjectServiceImpl.getInstance();
        List<SubjectDto> list = subjectService.getAll();
        System.out.println(list);
        req.setAttribute(Attribute.SUBJECTS, list);






        return Path.PAGE_HOME;
    }


}
