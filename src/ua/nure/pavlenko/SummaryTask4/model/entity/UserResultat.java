package ua.nure.pavlenko.SummaryTask4.model.entity;

import com.mysql.jdbc.TimeUtil;
import lombok.Data;

import java.util.List;

/**
 * Created by Alexander on 21.05.2017.
 */
@Data
public class UserResultat extends Entity {
    private TimeUtil dateStart;
    private TimeUtil dateFinish;
    private User user;
    private List<Answer> answers;
}
