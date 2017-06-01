package ua.nure.pavlenko.SummaryTask4.controller.filters;

import ua.nure.pavlenko.SummaryTask4.Path;
import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.exception.Massages;
import ua.nure.pavlenko.SummaryTask4.model.dto.UserDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander on 31.05.2017.
 */
@WebFilter( urlPatterns = {"/pages/jsp/test.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST},
        initParams = {@WebInitParam(name = Attribute.INDEX_PATH, value = Path.PAGE_LOGIN)}
)
public class FilterPassTest implements Filter {
    private String indexPath;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter(Attribute.INDEX_PATH);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        UserDto userDto = (UserDto) httpRequest.getSession().getAttribute(Attribute.USER);
        if(userDto == null) {
            httpRequest.setAttribute(Attribute.MASSAGE, Massages.ERROR_LOGIN_FOR_PASS_TEST);
            httpRequest.getRequestDispatcher(indexPath).forward(servletRequest,servletResponse);
//            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
