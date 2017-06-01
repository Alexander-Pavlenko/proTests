package ua.nure.pavlenko.SummaryTask4.controller.command;

import org.apache.log4j.Logger;
import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.exception.*;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander on 23.05.2017.
 */
public class Registration extends Command {
    final static Logger logger = Logger.getLogger(Registration.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String forward = Path.PAGE_REGISTRATION;
        if (request.getParameter(Attribute.GO_LOGIN)!=null){
            forward = Path.PAGE_LOGIN;
        } else if(request.getParameter(Attribute.REGISTRATION)!=null){
            try {
                UserServiceImpl.getInstance().save(request.getParameter(Attribute.PASSWORD),
                        request.getParameter(Attribute.LOGIN),
                        request.getParameter(Attribute.NAME),
                        request.getParameter(Attribute.E_MAIL));
                logger.info("Create new user: " + request.getParameter(Attribute.LOGIN));
                request.setAttribute(Attribute.MASSAGE, Massages.SUCCESS_REGISTRATION);
            } catch (ValidationException e) {
                request.setAttribute(Attribute.MASSAGE, e.getMessage());
            }catch (UniqueException e){
                boolean check = UserServiceImpl.getInstance().isFreeLogin(request.getParameter(Attribute.LOGIN));
                if(check) {
                    request.setAttribute(Attribute.MASSAGE, Massages.INFORM_EMAIL_BUSY);
                }else {
                    request.setAttribute(Attribute.MASSAGE, Massages.INFORM_LOGIN_BUSY);
                }
                System.out.println(e.getMessage());
            } catch (Exception e) {
                request.setAttribute(Attribute.MASSAGE, Massages.SERVER_ERROR);
                logger.error(e.getMessage());
            } finally {
                reurnRequest(request);
                forward = Path.PAGE_REGISTRATION;
            }
        }

        return forward;
    }

    private void reurnRequest(HttpServletRequest request){
        HttpServletRequest forwardRequest = request;
                forwardRequest.setAttribute(Attribute.LOGIN, request.getParameter(Attribute.LOGIN));
                forwardRequest.setAttribute(Attribute.NAME, request.getParameter(Attribute.NAME));
                forwardRequest.setAttribute(Attribute.E_MAIL, request.getParameter(Attribute.E_MAIL));
                request = forwardRequest;
    }
}
