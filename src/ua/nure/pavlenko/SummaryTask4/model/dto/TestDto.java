package ua.nure.pavlenko.SummaryTask4.model.dto;

import lombok.Data;
import ua.nure.pavlenko.SummaryTask4.model.entity.Entity;
import ua.nure.pavlenko.SummaryTask4.model.entity.Subject;
import ua.nure.pavlenko.SummaryTask4.model.entity.TypeOfTest;


/**
 * Created by Alexander on 24.05.2017.
 */
@Data
public class TestDto extends Entity{
    private String name;
    private Integer subject_id;
    private String description;
    private TypeOfTest typeOfTest;
    private String pathToIcon;
    private Integer timeTest;
}
