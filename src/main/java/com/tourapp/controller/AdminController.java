package com.tourapp.controller;

import com.tourapp.model.Itinerary;
import com.tourapp.service.BookingService;
import com.tourapp.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private ItineraryService itineraryService;
    @Autowired private BookingService bookingService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalTours", itineraryService.getAllActive().size());
        model.addAttribute("totalBookings", bookingService.countAll());
        model.addAttribute("recentBookings", bookingService.getAll().stream().limit(5).toList());
        return "admin/dashboard";
    }

    @GetMapping("/itineraries")
    public String itineraries(Model model) {
        model.addAttribute("itineraries", itineraryService.getAll());
        return "admin/itineraries";
    }

    @GetMapping("/itineraries/new")
    public String newItinerary(Model model) {
        model.addAttribute("itinerary", new Itinerary());
        model.addAttribute("isNew", true);
        return "admin/itinerary-form";
    }

    @GetMapping("/itineraries/edit/{id}")
    public String editItinerary(@PathVariable Long id, Model model) {
        itineraryService.getById(id).ifPresent(it -> model.addAttribute("itinerary", it));
        model.addAttribute("isNew", false);
        return "admin/itinerary-form";
    }

    @PostMapping("/itineraries/save")
    public String saveItinerary(@ModelAttribute Itinerary itinerary,
                                @RequestParam(required = false) String highlightsStr,
                                RedirectAttributes redirectAttributes) {
        if (highlightsStr != null && !highlightsStr.isBlank()) {
            itinerary.setHighlights(Arrays.asList(highlightsStr.split("\\s*,\\s*")));
        }
        itineraryService.save(itinerary);
        redirectAttributes.addFlashAttribute("message", "Tour saved successfully!");
        return "redirect:/admin/itineraries";
    }

    @PostMapping("/itineraries/delete/{id}")
    public String deleteItinerary(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        itineraryService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Tour removed.");
        return "redirect:/admin/itineraries";
    }

    @GetMapping("/bookings")
    public String bookings(Model model) {
        model.addAttribute("bookings", bookingService.getAll());
        return "admin/bookings";
    }

    @PostMapping("/bookings/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam String status,
                               RedirectAttributes redirectAttributes) {
        bookingService.updateStatus(id, status);
        redirectAttributes.addFlashAttribute("message", "Booking status updated.");
        return "redirect:/admin/bookings";
    }
}
