package ua.nure.pavlenko.SummaryTask4.model.entity;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexander on 20.05.2017.
 */
@Data
public class User extends Entity {
    private String login;
    private String password;
    private String first_name;
    private String last_name;
    private String phoneNumber;
    private String photoPath;
    private LocalDateTime dateRegestration;
    private String e_mail;
    private List<UserResultat> userResultants;
    private Role role;
    private Boolean isActivated;
    private Boolean isBlocked;


    public User() {
        dateRegestration = LocalDateTime.now();
        role = Role.USER;
    }
    public boolean setStatus (int status){
        if(status == 0){
            return false;
        }
        return true;
    }
    public void parseName (String name){
        String[] fullname = name.split(" ");
        this.setFirst_name(fullname[0]);
        this.setLast_name("");
        for(int i = 1; i < fullname.length; i++){
            this.setLast_name(this.getLast_name().concat(" ").concat(fullname[i]));
        }
    }

}
