package com.teachmeskills.market.model;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private LocalDateTime created;
    private LocalDateTime updated;
}