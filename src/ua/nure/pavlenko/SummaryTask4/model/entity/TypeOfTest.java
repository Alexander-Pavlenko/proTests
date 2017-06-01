package ua.nure.pavlenko.SummaryTask4.model.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
    public static List<String> getAllType(){
        List<String> list = new ArrayList<>();
        for(TypeOfTest typeOfTest : TypeOfTest.values()){
            list.add(typeOfTest.getType());
        }
        return list;
    }
}
