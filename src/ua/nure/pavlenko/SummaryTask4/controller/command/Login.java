package ua.nure.pavlenko.SummaryTask4.controller.command;

import org.apache.log4j.Logger;
import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.model.dto.UserDto;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander on 23.05.2017.
 */
public class Login extends Command {
    private static final Logger log = Logger.getLogger(Login.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String forward = Path.PAGE_LOGIN;
        if (request.getParameter(Attribute.GO_REGISTRATION) != null) {
            forward = Path.PAGE_REGISTRATION;
        } else if (request.getParameter(Attribute.LOGIN) != null) {

            if (request.getSession().getAttribute(Attribute.USER) == null) {
                try {
                    UserDto userDto = UserServiceImpl.getInstance()
                            .login(request.getParameter(Attribute.LOGIN),
                                    request.getParameter(Attribute.PASSWORD));

                    request.getSession().setAttribute(Attribute.USER, userDto);
                    log.info("User id = " + userDto.getId() + " login");
                    request.getSession().setAttribute(Attribute.LANGUAGE, Attribute.DEFAULT_LANGUAGE);
                    forward = Path.PAGE_CONTROLLER_HOME;
                } catch (AppException ex) {
                    request.setAttribute(Attribute.MASSAGE, ex.getMessage());
                    reurnRequest(request);
                    forward = Path.PAGE_LOGIN;
                }

            } else {
                forward = Path.PAGE_CONTROLLER_HOME;
            }

        }
        return forward;
    }
    private void reurnRequest(HttpServletRequest request) {
        HttpServletRequest forwardRequest = request;
        forwardRequest.setAttribute(Attribute.LOGIN, request.getParameter(Attribute.LOGIN));
    }
}
