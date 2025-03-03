package com.teachmeskills.market.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String firstname;
    private String secondName;
    private Integer age;
    private String email;
    private String sex;
    private String telephoneNumber;
    private String login;
    private String password;
}