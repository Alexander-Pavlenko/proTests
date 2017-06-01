package ua.nure.pavlenko.SummaryTask4.controller.command.AdminCommands;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.controller.command.Command;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.Massages;
import ua.nure.pavlenko.SummaryTask4.model.dto.SubjectDto;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by Alexander on 30.05.2017.
 */
public class CreateSubject extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nameSubject = request.getParameter(Attribute.NAME);
        String pathToIcon = request.getParameter(Attribute.ICON);
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setIcon(pathToIcon);
        subjectDto.setName(nameSubject);
        String massage = Massages.CREATE_SUCCESS;
        String forward = Path.COMMAND_ADMIN_OFFICE_TEST;
        try {
            SubjectServiceImpl.getInstance().save(subjectDto);
        } catch (AppException e) {
            massage = e.getMessage();
        }


        request.setAttribute(Attribute.MASSAGE, massage);

        return forward;
    }

}
