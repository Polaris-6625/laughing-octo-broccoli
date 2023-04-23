package com.example.dailysharing.enity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String root;
    private String password;
    private String sex;
}
