package ua.nure.pavlenko.SummaryTask4.controller.test;

import ua.nure.pavlenko.SummaryTask4.model.dto.SubjectDto;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexander on 21.05.2017.
 */
@WebServlet("/home")
public class test extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("pages/jsp/homePage.jsp").forward(req,resp);
        SubjectServiceImpl subjectService = SubjectServiceImpl.getInstance();
        List<SubjectDto> list = subjectService.getAll();
        System.out.println(list);
        req.setAttribute("subjects", list);
        req.getRequestDispatcher("/pages/jsp/home.jsp").forward(req,resp);
    }
}
