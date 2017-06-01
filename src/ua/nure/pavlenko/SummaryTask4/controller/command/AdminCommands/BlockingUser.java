package ua.nure.pavlenko.SummaryTask4.controller.command.AdminCommands;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.controller.command.Command;
import ua.nure.pavlenko.SummaryTask4.exception.Massages;
import ua.nure.pavlenko.SummaryTask4.exception.SuppressedException;
import ua.nure.pavlenko.SummaryTask4.model.entity.User;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander on 31.05.2017.
 */
public class BlockingUser extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer user_id = Integer.valueOf(request.getParameter(Attribute.USER_ID));
        Boolean action = Boolean.valueOf(request.getParameter(Attribute.ACTION));
        User user = UserServiceImpl.getInstance().getById(user_id);
        user.setIsBlocked(action);
        String forward = Path.COMMAND_ADMIN_OFFICE_USER;
        String massage = Massages.INFORM_UPDATE_SUCCESS;
        try {
            UserServiceImpl.getInstance().update(user);
        } catch (SuppressedException e) {
            for (Throwable exception : e.getSuppressed()){
                massage  += exception.getMessage();
            }
        }
        request.setAttribute(Attribute.MASSAGE, massage);
        return forward;
    }
}
