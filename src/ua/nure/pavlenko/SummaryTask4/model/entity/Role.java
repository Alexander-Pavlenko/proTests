package ua.nure.pavlenko.SummaryTask4.model.entity;

import ua.nure.pavlenko.SummaryTask4.controller.Attribute;

/**
 * Created by Alexander on 24.05.2017.
 */
public enum Role {
    USER(Attribute.USER),
    ADMIN(Attribute.ADMIN);

    private final String role;

    Role(String role) {
        this.role = role;
    }
    public String getType(){
        return role;
    }
}
