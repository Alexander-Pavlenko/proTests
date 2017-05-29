package ua.nure.pavlenko.SummaryTask4.model.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by Alexander on 21.05.2017.
 */
@Data
public class Question extends Entity {
    private String question;
    private String code;
    private Integer test_id;
    private List<Answer> answers;


    public static Integer returnCountRightAnswer(List<Answer> list) {
        int count = 0;
        for (Answer answer : list) {
            if (answer.getTruthful()) {
                count++;
            }
        }
        return count;
    }
}
