package com.teachmeskills.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String firstname;
    private String secondName;
    private Integer age;
    private String email;
    private String sex;
    private String telephoneNumber;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean isDeleted;
    private Security securityInfo;
}