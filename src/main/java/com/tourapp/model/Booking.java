package com.tourapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itineraryId;
    private String itineraryTitle;
    private String destination;

    private String travelerName;
    private String phone;
    private String email;
    private int numberOfPeople;
    private LocalDate travelDate;

    @Column(columnDefinition = "TEXT")
    private String specialRequests;

    private String status = "PENDING";

    private LocalDateTime createdAt = LocalDateTime.now();
}
