package ua.nure.pavlenko.SummaryTask4.model.dto;

import com.mysql.jdbc.TimeUtil;
import lombok.Data;
import ua.nure.pavlenko.SummaryTask4.model.entity.Entity;


/**
 * Created by Alexander on 23.05.2017.
 */
@Data
public class UserDto extends Entity {
    private String login;
    private String first_name;
    private String last_name;
    private String photoPath;
}
