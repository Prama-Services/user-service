package com.mycompany.model.user;

import lombok.Data;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private transient String password;
}
