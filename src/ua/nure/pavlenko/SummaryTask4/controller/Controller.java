package ua.nure.pavlenko.SummaryTask4.controller;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.command.Command;
import ua.nure.pavlenko.SummaryTask4.controller.command.CommandContainer;
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

//        LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        System.out.println(commandName);
//        LOG.trace("Request parameter: command --> " + commandName);
        if (commandName != null) {
            request.getSession().setAttribute("command", commandName);
        } else {
            commandName = (String) request.getSession().getAttribute("command");
        }
        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
//        LOG.trace("Obtained command --> " + command);

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
//        LOG.trace("Forward address --> " + forward);

//        LOG.debug("Controller finished, now go to forward address --> " + forward);

        // go to forward
//        response.sendRedirect(forward);
        if (forward != null) {
            request.getRequestDispatcher(forward).forward(request, response);
        }
    }
}
