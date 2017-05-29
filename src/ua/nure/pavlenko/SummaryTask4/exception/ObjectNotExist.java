package ua.nure.pavlenko.SummaryTask4.exception;

import java.sql.SQLException;

/**
 * Created by Alexander on 24.05.2017.
 */
public class ObjectNotExist extends AppException {
    public ObjectNotExist(String reason) {
        super(reason);
    }
}
