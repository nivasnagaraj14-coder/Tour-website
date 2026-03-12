package com.tourapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "itineraries")
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String destination;
    private String tourType; // DOMESTIC, INTERNATIONAL

    @Column(columnDefinition = "TEXT")
    private String description;

    private int duration;
    private double price;
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "itinerary_highlights", joinColumns = @JoinColumn(name = "itinerary_id"))
    @Column(name = "highlight")
    private List<String> highlights;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DayPlan> dayPlans;

    private boolean active = true;
}
