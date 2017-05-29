package ua.nure.pavlenko.SummaryTask4.controller.command;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander on 24.05.2017.
 */
public class SelectedLanguage extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String newLanguage = Attribute.LANGUAGE + request.getParameter(Attribute.LANGUAGE);
        request.getSession().setAttribute(Attribute.LANGUAGE, newLanguage);
        return Path.PAGE_CONTROLLER_HOME;
    }
}
