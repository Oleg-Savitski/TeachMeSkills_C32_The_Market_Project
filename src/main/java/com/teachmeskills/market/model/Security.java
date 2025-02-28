package com.teachmeskills.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Security {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long userId;
}