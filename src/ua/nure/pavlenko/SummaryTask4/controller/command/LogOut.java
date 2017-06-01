package ua.nure.pavlenko.SummaryTask4.controller.command;

import org.apache.log4j.Logger;
import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.model.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alexander on 30.05.2017.
 */
public class LogOut extends Command {
    final static Logger logger = Logger.getLogger(Login.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        logger.info("User " + ((UserDto)session.getAttribute(Attribute.USER)).getLogin() + " exit");
        session.invalidate();
        return Path.PAGE_LOGIN;
    }
}
