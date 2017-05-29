//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ua.nure.pavlenko.SummaryTask4.model.entity;

import lombok.Data;

import java.util.List;

@Data
public class Test extends Entity{
    private String name;
    private Integer subject_id;
    private String description;
    private TypeOfTest typeOfTest;
    private String pathToIcon;
    private Subject subject;
    private Integer timeTest;
    private List<Question> questions;

}
