package ua.nure.pavlenko.SummaryTask4.exception;

/**
 * Created by Alexander on 31.05.2017.
 */
public class SuppressedException extends Error {
    public SuppressedException(String message) {
        super(message);
    }
    public SuppressedException(){}
}
