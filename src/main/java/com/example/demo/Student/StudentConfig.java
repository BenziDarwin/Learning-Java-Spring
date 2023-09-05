package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student benjamin = new Student(
                    "Benjamin",
                    "ssalibenjamin0402@gmail.com",
                    LocalDate.of(2002,12,23)
            );
            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2005,12,23)
            );
            repository.saveAll(List.of(benjamin,alex));
        };
    }

}
