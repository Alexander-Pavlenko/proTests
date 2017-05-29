package ua.nure.pavlenko.SummaryTask4.model.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by Alexander on 21.05.2017.
 */
@Data
public class Subject extends Entity {
    private String name;
    private String icon;
    private List<Test> tests;
}
