package ua.nure.pavlenko.SummaryTask4.controller.command;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.model.dto.UserDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Role;
import ua.nure.pavlenko.SummaryTask4.model.entity.User;
import ua.nure.pavlenko.SummaryTask4.model.entity.UserResult;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.UserResultServiceImpl;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexander on 30.05.2017.
 */
public class Profile extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute(Attribute.USER);
        List<UserResult> userResults = null;
        String forward = Path.PAGE_PROFILE;
        try {
             userResults = UserResultServiceImpl.getInstance().getUserResultByUserId(user.getId());
        } catch (AppException e) {
            request.setAttribute(Attribute.MASSAGE, e.getMessage());
        }
        Boolean isAdmin = user.getRole() == Role.ADMIN;
        request.setAttribute(Attribute.IS_ADMIN,isAdmin);
        request.setAttribute(Attribute.RESULTS, userResults);

        return forward;
    }
}
