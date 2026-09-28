package com.nutricare.controller;

import com.nutricare.model.Appointment;
import com.nutricare.model.Dietitian;
import com.nutricare.model.User;
import com.nutricare.repository.AppointmentRepository;
import com.nutricare.repository.DietitianRepository;
import com.nutricare.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class PageController {

    private final DietitianRepository dietitianRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PageController(DietitianRepository dietitianRepository,
                          AppointmentRepository appointmentRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.dietitianRepository = dietitianRepository;
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/patient/register";
    }

    @GetMapping("/patient/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/patient/register")
    public String saveRegistration(@ModelAttribute User user, Model model) {
        String email = user.getEmail() == null ? "" : user.getEmail().trim().toLowerCase();
        String name = user.getName() == null ? "" : user.getName().trim();
        String password = user.getPassword() == null ? "" : user.getPassword();

        if (name.isBlank() || email.isBlank() || password.length() < 6) {
            model.addAttribute("error", "Please enter a valid name, email and password of at least 6 characters.");
            user.setEmail(email);
            user.setName(name);
            return "register";
        }

        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "An account with this email already exists. Please login.");
            user.setEmail(email);
            return "register";
        }

        user.setName(name);
        user.setEmail(email);
        user.setRole("PATIENT");
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return "redirect:/login?registered=true";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        User user = currentUser(authentication);
        model.addAttribute("userName", user.getName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("dietitians", dietitianRepository.findAll());
        return "index";
    }

    @GetMapping("/dietitians")
    public String dietitians(Model model, Authentication authentication) {
        User user = currentUser(authentication);
        model.addAttribute("userName", user.getName());
        model.addAttribute("dietitians", dietitianRepository.findAll());
        return "dietitians";
    }

    @GetMapping("/book")
    public String booking(Model model, Authentication authentication) {
        User user = currentUser(authentication);
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("dietitians", dietitianRepository.findAll());
        model.addAttribute("userName", user.getName());
        model.addAttribute("userEmail", user.getEmail());
        return "book-appointment";
    }

    @PostMapping("/book")
    public String saveAppointment(@ModelAttribute Appointment appointment,
                                  Authentication authentication,
                                  Model model) {
        User user = currentUser(authentication);

        if (appointment.getDietitianId() == null || appointment.getAppointmentDate() == null
                || appointment.getAppointmentTime() == null || appointment.getAppointmentTime().isBlank()) {
            model.addAttribute("error", "Please select a dietitian, date and time.");
            return bookingWithError(model, user, appointment);
        }

        if (appointment.getAppointmentDate().isBefore(LocalDate.now())) {
            model.addAttribute("error", "Appointment date cannot be in the past.");
            return bookingWithError(model, user, appointment);
        }

        Optional<Dietitian> dietitian = dietitianRepository.findById(appointment.getDietitianId());
        if (dietitian.isEmpty()) {
            model.addAttribute("error", "Selected dietitian was not found.");
            return bookingWithError(model, user, appointment);
        }

        appointment.setPatientName(user.getName());
        appointment.setPatientEmail(user.getEmail());
        appointment.setDietitianName(dietitian.get().getName());
        appointment.setStatus("PENDING");
        appointmentRepository.save(appointment);

        return "redirect:/appointments?success=true";
    }

    @GetMapping("/appointments")
    public String appointments(Model model,
                               Authentication authentication,
                               @RequestParam(value = "success", required = false) String success) {
        User user = currentUser(authentication);
        model.addAttribute("userName", user.getName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("appointments",
                appointmentRepository.findByPatientEmailOrderByAppointmentDateAscAppointmentTimeAsc(user.getEmail()));
        if ("true".equals(success)) {
            model.addAttribute("successMessage", "Appointment booked successfully.");
        }
        return "appointments";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                Model model) {
        String normalizedEmail = email == null ? "" : email.trim().toLowerCase();

        if (password == null || password.length() < 6) {
            model.addAttribute("error", "Password must contain at least 6 characters.");
            return "forgot-password";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "forgot-password";
        }

        User user = userRepository.findByEmail(normalizedEmail).orElse(null);
        if (user == null) {
            model.addAttribute("error", "No account found with this email.");
            return "forgot-password";
        }

        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return "redirect:/login?reset=true";
    }

    private User currentUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Logged-in user was not found."));
    }

    private String bookingWithError(Model model, User user, Appointment appointment) {
        model.addAttribute("appointment", appointment);
        model.addAttribute("dietitians", dietitianRepository.findAll());
        model.addAttribute("userName", user.getName());
        model.addAttribute("userEmail", user.getEmail());
        return "book-appointment";
    }
}
