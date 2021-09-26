package com.project.webstore.config;

import com.project.webstore.services.DBService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    
    @Autowired
    private DBService dbservice;
    
    @Bean
    public boolean instantiateDataBase() throws ParseException{
        dbservice.instantiateTestDatabase();
        return true;
    }
    
}
