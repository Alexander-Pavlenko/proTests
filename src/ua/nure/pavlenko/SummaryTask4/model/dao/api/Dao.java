package ua.nure.pavlenko.SummaryTask4.model.dao.api;

/**
 * Created by Alexandr on 16.12.2016.
 */



import ua.nure.pavlenko.SummaryTask4.exception.AppException;
import ua.nure.pavlenko.SummaryTask4.model.entity.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    List<T> getAll();
    T getById(Integer key);
    T save(T entity) throws AppException;
    void delete(Integer key);
    void update(T entity) throws AppException;
}