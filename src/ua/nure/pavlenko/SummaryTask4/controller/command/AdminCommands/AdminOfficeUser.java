package ua.nure.pavlenko.SummaryTask4.controller.command.AdminCommands;


import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.controller.command.Command;

import ua.nure.pavlenko.SummaryTask4.model.entity.User;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexander on 31.05.2017.
 */
public class AdminOfficeUser extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String forward = Path.PAGE_ADMIN_OFFICE_USER;
        String massage = "";
        List<User> users = UserServiceImpl.getInstance().getAll();

        request.setAttribute(Attribute.USERS, users);
        return forward;

    }
}
