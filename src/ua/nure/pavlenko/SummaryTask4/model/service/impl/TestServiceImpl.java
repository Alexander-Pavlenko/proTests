package ua.nure.pavlenko.SummaryTask4.model.service.impl;

import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.exception.UnsupportedException;
import ua.nure.pavlenko.SummaryTask4.exception.ObjectNotExist;
import ua.nure.pavlenko.SummaryTask4.model.dao.DaoFactory;
import ua.nure.pavlenko.SummaryTask4.model.dao.api.Dao;
import ua.nure.pavlenko.SummaryTask4.model.dao.impl.TestDaoImpl;
import ua.nure.pavlenko.SummaryTask4.model.dto.FilterDto;
import ua.nure.pavlenko.SummaryTask4.model.dto.TestDto;

import ua.nure.pavlenko.SummaryTask4.model.entity.Test;
import ua.nure.pavlenko.SummaryTask4.model.entity.TypeOfTest;
import ua.nure.pavlenko.SummaryTask4.model.service.api.Service;
import ua.nure.pavlenko.SummaryTask4.utils.mapper.BeanMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 24.05.2017.
 */
public class TestServiceImpl implements Service<TestDto> {
    private static TestServiceImpl testService;
    private Dao<Test> testDao;
    private BeanMapper beanMapper;

    private TestServiceImpl() {
        testDao = DaoFactory.getInstance().getTestDao();
        beanMapper = BeanMapper.getInstance();
    }

    public static synchronized TestServiceImpl getInstance() {
        if (testService == null) {
            testService = new TestServiceImpl();
        }
        return testService;
    }

    @Override
    public List<TestDto> getAll() {
        List<Test> list = testDao.getAll();
        List<TestDto> dtoList = beanMapper.listMapToList(list, TestDto.class);
        return dtoList;
    }

    @Override
    public TestDto getById(Integer id) {
        Test test = testDao.getById(id);
        TestDto testDto = beanMapper.singleMapper(test,TestDto.class);
        return testDto;
    }

    @Override
    public TestDto save(TestDto entity) throws AppException {
        Test test = beanMapper.singleMapper(entity, Test.class);
        testDao.save(test);
        return null;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public void update(TestDto entity) {

    }

    public List<TestDto> findTestByParameter(HttpServletRequest request) throws ObjectNotExist {
        FilterDto filterDao = parseRequest(request);
        List<Test> tests = ((TestDaoImpl) testDao).findTestsByParameter(filterDao);
        List<TestDto> testDtos = beanMapper.listMapToList(tests, TestDto.class);
        return testDtos;

    }


    private FilterDto parseRequest(HttpServletRequest request) {
        FilterDto filterDao = new FilterDto();
        filterDao.setName(setDefaultValue(request.getParameter(Attribute.SEARCH)));
        filterDao.setSubject_id(setDefaultValue((String) request.getAttribute(Attribute.SUBJECT_ID)));

        filterDao.setType_course(setDefaultValue(request.getParameter(Attribute.TYPE_COURSE)));
        filterDao.setType_module(setDefaultValue(request.getParameter(Attribute.TYPE_MODULE)));
        filterDao.setType_topic(setDefaultValue(request.getParameter(Attribute.TYPE_TOPIC)));

        request.setAttribute(Attribute.SEARCH, returnSearch(filterDao.getName()));
        if (chekFilters(filterDao)) {

            request.setAttribute(Attribute.TYPE_TOPIC, returnResp(filterDao.getType_topic()));
            request.setAttribute(Attribute.TYPE_MODULE, returnResp(filterDao.getType_module()));
            request.setAttribute(Attribute.TYPE_COURSE, returnResp(filterDao.getType_course()));
        }

        return filterDao;

    }

    private String returnSearch(String text) {
        return text.equals("%") ? "" : text;
    }

    private String returnResp(String text) {
        return !text.equals("-") ? Attribute.CHECKED : "";
    }

    private boolean chekFilters(FilterDto filterDao) {
        boolean check;
        check = !filterDao.getType_course().equals("%")
                || !filterDao.getType_module().equals("%")
                || !filterDao.getType_topic().equals("%")
                ? true : false;
        if(filterDao.getSubject_id().equals("0")){
            filterDao.setSubject_id("%");
        }
        if (!check) {
            return check;
        }
        if (filterDao.getType_course().equals("%")) {
            filterDao.setType_course("-");
        }
        if (filterDao.getType_module().equals("%")) {
            filterDao.setType_module("-");
        }
        if (filterDao.getType_topic().equals("%")) {
            filterDao.setType_topic("-");
        }
        return check;
    }

    private String setDefaultValue(String parameter) {
        if (parameter == null) {
            return "%";
        }
        return parameter;
    }

    public List<TestDto> filterByType(List<TestDto> list, TypeOfTest typeOfTest){
        List<TestDto> result = new ArrayList<>();
        for(TestDto testDto : list){
            if(testDto.getTypeOfTest().equals(typeOfTest)){
                result.add(testDto);
            }
        }
        return result;
    }

}
