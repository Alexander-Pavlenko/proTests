package ua.nure.pavlenko.SummaryTask4.controller.listner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.pavlenko.SummaryTask4.model.dto.SubjectDto;
import ua.nure.pavlenko.SummaryTask4.model.dto.TestDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Test;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.SubjectServiceImpl;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.TestServiceImpl;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexander on 25.05.2017.
 */
@WebListener
public class ContextListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String log4jFilePath = servletContext.getRealPath("WEB-INF/log4j.properties");
        PropertyConfigurator.configure(log4jFilePath);
        SubjectServiceImpl.getInstance();
        TestServiceImpl.getInstance();
        UserServiceImpl.getInstance();


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
