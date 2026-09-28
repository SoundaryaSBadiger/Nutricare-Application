package com.nutricare.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private String patientEmail;

    @Column(nullable = false)
    private Long dietitianId;

    @Column(nullable = false)
    private String dietitianName;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    private String appointmentTime;

    @Column(nullable = false)
    private String status = "PENDING";

    public Appointment() {}

    public Long getId() { return id; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getPatientEmail() { return patientEmail; }
    public void setPatientEmail(String patientEmail) { this.patientEmail = patientEmail; }
    public Long getDietitianId() { return dietitianId; }
    public void setDietitianId(Long dietitianId) { this.dietitianId = dietitianId; }
    public String getDietitianName() { return dietitianName; }
    public void setDietitianName(String dietitianName) { this.dietitianName = dietitianName; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
