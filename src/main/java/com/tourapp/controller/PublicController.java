package com.tourapp.controller;

import com.tourapp.model.Booking;
import com.tourapp.model.Itinerary;
import com.tourapp.service.BookingService;
import com.tourapp.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class PublicController {

    @Autowired private ItineraryService itineraryService;
    @Autowired private BookingService bookingService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tours", itineraryService.getAllActive());
        return "index";
    }

    @GetMapping("/tours")
    public String tours(@RequestParam(required = false) String type, Model model) {
        if (type != null && !type.isEmpty()) {
            model.addAttribute("tours", itineraryService.getByType(type));
            model.addAttribute("activeFilter", type);
        } else {
            model.addAttribute("tours", itineraryService.getAllActive());
            model.addAttribute("activeFilter", "ALL");
        }
        return "tours";
    }

    @GetMapping("/tours/{id}")
    public String tourDetail(@PathVariable Long id, Model model) {
        Optional<Itinerary> itinerary = itineraryService.getById(id);
        if (itinerary.isEmpty()) return "redirect:/tours";
        model.addAttribute("tour", itinerary.get());
        model.addAttribute("booking", new Booking());
        return "tour-detail";
    }

    @PostMapping("/booking/submit")
    public String submitBooking(@ModelAttribute Booking booking,
                                RedirectAttributes redirectAttributes) {
        itineraryService.getById(booking.getItineraryId()).ifPresent(tour -> {
            booking.setItineraryTitle(tour.getTitle());
            booking.setDestination(tour.getDestination());
        });
        bookingService.save(booking);
        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("bookingName", booking.getTravelerName());
        return "redirect:/booking/confirmation";
    }

    @GetMapping("/booking/confirmation")
    public String bookingConfirmation() {
        return "booking-confirmation";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
