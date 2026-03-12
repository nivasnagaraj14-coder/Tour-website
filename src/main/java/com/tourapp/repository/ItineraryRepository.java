package com.tourapp.repository;

import com.tourapp.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    List<Itinerary> findByActiveTrue();
    List<Itinerary> findByTourTypeAndActiveTrue(String tourType);
}
