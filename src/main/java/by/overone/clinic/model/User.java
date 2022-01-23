package by.overone.clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
//@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
//    @Column(unique = true)
    private String login;
    private String password;
//    @Column(unique = true)
    private String email;
    private String role;
    private String status;
}
