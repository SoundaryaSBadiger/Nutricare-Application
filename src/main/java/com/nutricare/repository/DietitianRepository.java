package com.nutricare.repository;

import com.nutricare.model.Dietitian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietitianRepository extends JpaRepository<Dietitian, Long> {
}
