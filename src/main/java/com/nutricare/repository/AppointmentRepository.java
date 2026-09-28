package com.nutricare.repository;

import com.nutricare.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientEmailOrderByAppointmentDateAscAppointmentTimeAsc(String patientEmail);
}
