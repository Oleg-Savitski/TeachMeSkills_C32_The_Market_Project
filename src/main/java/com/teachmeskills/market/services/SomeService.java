package com.teachmeskills.market.services;

import com.teachmeskills.market.utils.config.database.DatabaseConfig;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SomeService {
    private final DatabaseConfig databaseConfig;

    @Autowired
    public SomeService(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }
}