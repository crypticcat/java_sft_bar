package sft.bar.mantis.model;

import javax.persistence.*;

@Entity
@Table(name = "mantis_user_mantis")
public class UserData {

    @Id
    @Column//can omit if variable name is the same as the table column name
    private int id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
