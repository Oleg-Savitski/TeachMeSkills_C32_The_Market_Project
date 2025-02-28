package com.teachmeskills.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Security {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long userId;
}