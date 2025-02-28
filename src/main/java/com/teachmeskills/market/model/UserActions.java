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
public class UserActions {
    private Long id;
    private Long userId;
    private Long productId;
    private String action;
    private LocalDateTime actionTime;
}