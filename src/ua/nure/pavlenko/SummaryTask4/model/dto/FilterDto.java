package ua.nure.pavlenko.SummaryTask4.model.dto;

import lombok.Data;

/**
 * Created by Alexander on 24.05.2017.
 */
@Data
public class FilterDto {
    private String name;
    private String subject_id;
    private String type_module;
    private String type_topic;
    private String type_course;

}
