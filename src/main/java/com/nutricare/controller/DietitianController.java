package com.nutricare.controller;

import com.nutricare.model.Dietitian;
import com.nutricare.repository.DietitianRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dietitians")
public class DietitianController {

    private final DietitianRepository repository;

    public DietitianController(DietitianRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Dietitian> all() {
        return repository.findAll();
    }
}
