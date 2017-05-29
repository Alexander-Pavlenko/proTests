package test;

import ua.nure.pavlenko.SummaryTask4.database.dataSource.DataSource;
import ua.nure.pavlenko.SummaryTask4.model.dao.DaoFactory;
import ua.nure.pavlenko.SummaryTask4.model.dao.api.Dao;
import ua.nure.pavlenko.SummaryTask4.model.entity.Subject;
import ua.nure.pavlenko.SummaryTask4.model.entity.TypeOfTest;
import ua.nure.pavlenko.SummaryTask4.model.service.impl.SubjectServiceImpl;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Alexander on 20.05.2017.
 */
public class main {
    public static void main(String[] args) {
        TypeOfTest test = TypeOfTest.valueOf("MODULE");
        System.out.println(test);

    }
}
