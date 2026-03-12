package com.tourapp.service;

import com.tourapp.model.Booking;
import com.tourapp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking save(Booking booking) {
        booking.setStatus("PENDING");
        booking.setCreatedAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    public List<Booking> getAll() {
        return bookingRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Booking> getById(Long id) {
        return bookingRepository.findById(id);
    }

    public void updateStatus(Long id, String status) {
        bookingRepository.findById(id).ifPresent(b -> {
            b.setStatus(status);
            bookingRepository.save(b);
        });
    }

    public long countAll() {
        return bookingRepository.count();
    }
}
