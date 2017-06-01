package ua.nure.pavlenko.SummaryTask4.model.entity;

import com.mysql.jdbc.TimeUtil;
import lombok.Data;
import ua.nure.pavlenko.SummaryTask4.model.dto.TestDto;
import ua.nure.pavlenko.SummaryTask4.model.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexander on 21.05.2017.
 */
@Data
public class UserResult extends Entity {
    private LocalDateTime dateStart;
    private LocalDateTime dateFinish;
    private UserDto userDto;
    private TestDto testDto;
    private Integer result;
    private List<Answer> answers;
}
