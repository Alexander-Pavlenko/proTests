package ua.nure.pavlenko.SummaryTask4.model.service.impl;

import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.model.dao.DaoFactory;
import ua.nure.pavlenko.SummaryTask4.model.dao.api.Dao;
import ua.nure.pavlenko.SummaryTask4.model.dto.SubjectDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Subject;
import ua.nure.pavlenko.SummaryTask4.model.service.api.Service;
import ua.nure.pavlenko.SummaryTask4.utils.mapper.BeanMapper;

import java.util.List;

/**
 * Created by Alexander on 21.05.2017.
 */
public class SubjectServiceImpl implements Service<SubjectDto>{
    private  static SubjectServiceImpl subjectService;
    private Dao<Subject> subjectDao;
    private BeanMapper beanMapper;

    private SubjectServiceImpl(){
        subjectDao = DaoFactory.getInstance().getSubjectDao();
        beanMapper = BeanMapper.getInstance();
    }

    public  static synchronized SubjectServiceImpl getInstance(){
        if(subjectService == null){
            subjectService = new SubjectServiceImpl();
        }
        return subjectService;
    }
    @Override
    public List<SubjectDto> getAll() {
        List<Subject> subjects = subjectDao.getAll();
        List<SubjectDto> subjectDtos = beanMapper.listMapToList(subjects,
                SubjectDto.class);
        return subjectDtos;


    }
    public SubjectDto getByIdBySession(List<SubjectDto> list, String id){
        for(SubjectDto subjectDto1: list){
            if(subjectDto1.getId().equals(Integer.parseInt(id))){
                return subjectDto1;
            }
        }
        return null;
    }
    public SubjectDto createDefaultValue(){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Tests");
        subjectDto.setIcon("/pages/img/icon/java_icon.png");
        return subjectDto;
    }

    @Override
    public SubjectDto getById(Integer id) {
        return null;
    }

    @Override
    public SubjectDto save(SubjectDto entity) throws AppException {
        Subject subject = beanMapper.singleMapper(entity, Subject.class);
        subjectDao.save(subject);
        return null;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public void update(SubjectDto entity) {

    }


}
