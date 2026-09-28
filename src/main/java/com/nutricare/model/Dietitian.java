package com.nutricare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dietitians")
public class Dietitian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String qualification;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private int experience;

    @Column(nullable = false)
    private double consultationFee;

    public Dietitian() {}

    public Dietitian(String name, String qualification, String specialization,
                      int experience, double consultationFee) {
        this.name = name;
        this.qualification = qualification;
        this.specialization = specialization;
        this.experience = experience;
        this.consultationFee = consultationFee;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }
}
