package by.overone.clinic.model;

import lombok.Data;

@Data

public class User {
    private long id;
    private String login;
    private String password;
    private String email;

    public User(long id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(){

    }

    @Override
    public String toString() {
        return "User(" +
                "id=" + id +
                ", login=" + login +
                ", password=" + password +
                ", email=" + email +
                ')';
    }
}
