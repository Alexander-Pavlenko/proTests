package ua.nure.pavlenko.SummaryTask4.model.entity;

import lombok.Data;

/**
 * Created by Alexander on 21.05.2017.
 */
@Data
public class Answer extends Entity {
    private Integer question_id;
    private String answer;
    private Boolean truthful;

    public Answer() {
    }

    public Answer(Integer question_id, String answer, Boolean truthful) {
        this.question_id = question_id;
        this.answer = answer;
        this.truthful = truthful;
    }
}
