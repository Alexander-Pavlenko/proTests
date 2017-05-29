package ua.nure.pavlenko.SummaryTask4.exception;

import java.io.IOException;

/**
 * Created by Alexander on 23.05.2017.
 */
public class AppException extends Exception {
    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
