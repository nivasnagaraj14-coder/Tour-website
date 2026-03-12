package com.tourapp.service;

import com.tourapp.model.Itinerary;
import com.tourapp.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    public List<Itinerary> getAllActive() {
        return itineraryRepository.findByActiveTrue();
    }

    public List<Itinerary> getAll() {
        return itineraryRepository.findAll();
    }

    public List<Itinerary> getByType(String type) {
        return itineraryRepository.findByTourTypeAndActiveTrue(type);
    }

    public Optional<Itinerary> getById(Long id) {
        return itineraryRepository.findById(id);
    }

    public Itinerary save(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    public void delete(Long id) {
        itineraryRepository.findById(id).ifPresent(it -> {
            it.setActive(false);
            itineraryRepository.save(it);
        });
    }
}
