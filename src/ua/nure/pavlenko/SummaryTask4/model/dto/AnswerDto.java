package ua.nure.pavlenko.SummaryTask4.model.dto;

import lombok.Data;
import ua.nure.pavlenko.SummaryTask4.model.entity.Entity;

/**
 * Created by Alexander on 27.05.2017.
 */
@Data
public class AnswerDto extends Entity {
    private Integer question_id;
    private String answer;
    private Boolean truthful;
}
