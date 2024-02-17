package com.leonlib.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.leonlib.model") // Reemplaza "com.leonlib.model" con la ubicación real de tu clase Book
public class JpaConfiguration {
    // Otras configuraciones si las tienes
}