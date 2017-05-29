package ua.nure.pavlenko.SummaryTask4.model.entity;

/**
 * Created by Alexander on 21.05.2017.
 */
public enum TypeOfTest {
    TOPIC("topic"),
    MODULE("module"),
    COURSE("course");

    private final String type;

    TypeOfTest(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
