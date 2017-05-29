package ua.nure.pavlenko.SummaryTask4.model.service.api;



import ua.nure.pavlenko.SummaryTask4.exception.UnsupportedException;
import ua.nure.pavlenko.SummaryTask4.model.entity.Entity;

import java.util.List;

/**
 * Created by Alexandr on 16.12.2016.
 */
public interface Service<T extends Entity> {
    List<T> getAll();
    T getById(Integer id);
    T save(T entity) throws UnsupportedException;
    void delete(Integer key);
    void update(T entity);

}
