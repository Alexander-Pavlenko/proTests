package ua.nure.pavlenko.SummaryTask4.utils;

import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.model.dto.QuestionAnswerDto;
import ua.nure.pavlenko.SummaryTask4.model.entity.Answer;
import ua.nure.pavlenko.SummaryTask4.model.entity.Entity;
import ua.nure.pavlenko.SummaryTask4.model.entity.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 29.05.2017.
 */
public class CommandUtil {
    public static String chooseBox(List<Answer> answers) {
        if (Question.returnCountRightAnswer(answers) > 1) {
            return Attribute.CHECKBOX;
        }
        return Attribute.RADIO;
    }

    public static Integer check(Integer index, List<?> list) {
        if (index >= 0 && index < list.size()) {
            return index;
        }
        return -1;
    }


    public static <T extends Entity> T returnElementById(List<T> list, String id) {

        for (T t : list) {
            if (t.getId().equals(Integer.parseInt(id))) {
                return t;
            }
        }
        return null;
    }

    public static Integer returnResult(List<QuestionAnswerDto> listTest) {
        double mark = 0;

        for (QuestionAnswerDto questionAnswerDto : listTest) {
            double questionMark = 0;
            List<Answer> allAnswer = questionAnswerDto.getQuestion().getAnswers();
            Integer count = Question.returnCountRightAnswer(allAnswer);

            boolean isCorrect = true;
            for (Answer aswer : questionAnswerDto.getAnswers()) {
                if (!aswer.getTruthful()) {
                    isCorrect = false;
                    break;
                }
            }
            if (isCorrect) {
               questionMark = 1. / count * questionAnswerDto.getAnswers().size();
            }
            mark+= (100 / listTest.size()) * questionMark;
        }
        return (int)mark;
    }

    public static List<Answer> getAnswerToList(List<QuestionAnswerDto> list){
        List<Answer> answers = new ArrayList<>();
        for(QuestionAnswerDto questionAnswerDto : list){
            for(Answer answer : questionAnswerDto.getAnswers()){
                answers.add(answer);
            }
        }
        return answers;
    }
}
