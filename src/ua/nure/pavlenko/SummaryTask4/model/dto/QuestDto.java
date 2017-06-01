package ua.nure.pavlenko.SummaryTask4.model.dto;

import lombok.Data;
import ua.nure.pavlenko.SummaryTask4.model.entity.Entity;

/**
 * Created by Alexander on 26.05.2017.
 */
@Data
public class QuestDto extends Entity {
    Integer index;

    public QuestDto(Integer id, Integer index) {
        this.setId(id);
        this.index = index;
    }
}
