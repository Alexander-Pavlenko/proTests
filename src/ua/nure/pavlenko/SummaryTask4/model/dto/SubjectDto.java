package ua.nure.pavlenko.SummaryTask4.model.dto;

import lombok.Data;
import ua.nure.pavlenko.SummaryTask4.model.entity.Entity;

/**
 * Created by Alexander on 21.05.2017.
 */
@Data
public class SubjectDto extends Entity {
    private String name;
    private String icon;
}
