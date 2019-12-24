package com.canban.canbanapp.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper mapper(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
