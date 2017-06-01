package ua.nure.pavlenko.SummaryTask4.controller;

import org.apache.log4j.Logger;
import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.command.Command;
import ua.nure.pavlenko.SummaryTask4.controller.command.CommandContainer;
import ua.nure.pavlenko.SummaryTask4.controller.command.Registration;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander on 21.05.2017.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(Registration.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }


    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        logger.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        logger.trace("Request parameter: command --> " + commandName);
        if (commandName != null) {
            request.getSession().setAttribute("command", commandName);
        } else {
            commandName = (String) request.getSession().getAttribute("command");
        }
        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        logger.trace("Obtained command --> " + command);

        // execute command and get forward address
        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        if (request.getSession().getAttribute(Attribute.SUBJECTS) == null) {
            request.getSession().setAttribute(Attribute.SUBJECTS, SubjectServiceImpl.getInstance().getAll());
        }
        logger.trace("Forward address --> " + forward);

        logger.debug("Controller finished, now go to forward address --> " + forward);

        // go to forward
        if (forward != null) {
            request.getRequestDispatcher(forward).forward(request, response);
        }
    }
}
