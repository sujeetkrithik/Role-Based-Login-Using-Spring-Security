package com.example.model;


import lombok.Data;

import javax.persistence.*;

@Entity(name="user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private String role;
}
