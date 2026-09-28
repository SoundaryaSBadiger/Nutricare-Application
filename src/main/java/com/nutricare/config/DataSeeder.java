package com.nutricare.config;

import com.nutricare.model.Dietitian;
import com.nutricare.repository.DietitianRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDietitians(DietitianRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Dietitian(
                    "Dr. Ananya Sharma", "M.Sc. Nutrition", "Weight Management", 6, 800));
                repository.save(new Dietitian(
                    "Dr. Riya Mehta", "RD, PG Diploma", "Diabetes Nutrition", 8, 1000));
                repository.save(new Dietitian(
                    "Dr. Kavya Rao", "M.Sc. Dietetics", "Women's Wellness", 5, 700));
                repository.save(new Dietitian(
                    "Dr. Neha Iyer", "M.Sc. Clinical Nutrition", "PCOS & Lifestyle Nutrition", 7, 900));
            }
        };
    }
}
