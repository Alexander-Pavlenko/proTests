package ua.nure.pavlenko.SummaryTask4.model.dto;

import lombok.Data;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;

import java.util.List;

/**
 * Created by Alexander on 27.05.2017.
 */
@Data
public class QuestionAnswerDto {
    Question question;
    List<Answer> answers;

    public QuestionAnswerDto(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }
}
