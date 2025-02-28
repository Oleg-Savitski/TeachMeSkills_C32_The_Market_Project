package com.teachmeskills.market.model;

import lombok.Getter;

@Getter
public enum Role {
    USER("The average user"),
    ADMIN("Administrator"),
    MODERATOR("Moderator");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}