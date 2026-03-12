package com.tourapp.config;

import com.tourapp.model.Itinerary;
import com.tourapp.model.DayPlan;
import com.tourapp.model.User;
import com.tourapp.repository.ItineraryRepository;
import com.tourapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private ItineraryRepository itineraryRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(2000); // Wait for schema creation
        
        // Create admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@simplytours.com");
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
            System.out.println("Admin user created: admin / admin123");
        }

        // Sample itineraries
        if (itineraryRepository.count() == 0) {
            Itinerary t1 = new Itinerary();
            t1.setTitle("Golden Triangle India");
            t1.setDestination("Delhi - Agra - Jaipur");
            t1.setTourType("DOMESTIC");
            t1.setDescription("Explore the iconic Golden Triangle route through India's most historic cities filled with Mughal heritage and royal Rajput forts.");
            t1.setDuration(7);
            t1.setPrice(25000);
            t1.setImageUrl("https://images.unsplash.com/photo-1524492412937-b28074a5d7da?w=800");
            t1.setHighlights(Arrays.asList("Taj Mahal Sunrise", "Amber Fort", "Qutub Minar", "Local Bazaar"));
            t1.setActive(true);

            List<DayPlan> days1 = new ArrayList<>();
            days1.add(makeDayPlan(t1, 1, "Arrival in Delhi", "Check-in, Qutub Minar and Humayun's Tomb visit"));
            days1.add(makeDayPlan(t1, 2, "Delhi Sightseeing", "Red Fort, Jama Masjid, India Gate"));
            days1.add(makeDayPlan(t1, 3, "Delhi to Agra", "Drive to Agra, Agra Fort evening"));
            days1.add(makeDayPlan(t1, 4, "Taj Mahal Day", "Sunrise Taj Mahal, Fatehpur Sikri"));
            days1.add(makeDayPlan(t1, 5, "Agra to Jaipur", "Drive via Bharatpur Bird Sanctuary"));
            days1.add(makeDayPlan(t1, 6, "Jaipur Sightseeing", "Amber Fort, City Palace, Hawa Mahal"));
            days1.add(makeDayPlan(t1, 7, "Departure", "Shopping and airport drop"));
            t1.setDayPlans(days1);

            Itinerary t2 = new Itinerary();
            t2.setTitle("Kerala Backwaters Bliss");
            t2.setDestination("Kochi - Munnar - Alleppey");
            t2.setTourType("DOMESTIC");
            t2.setDescription("A serene journey through Kerala's misty hill stations, lush tea gardens, and tranquil backwaters on a traditional houseboat.");
            t2.setDuration(6);
            t2.setPrice(22000);
            t2.setImageUrl("https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?w=800");
            t2.setHighlights(Arrays.asList("Houseboat Stay", "Tea Garden Trek", "Kathakali Dance", "Chinese Fishing Nets"));
            t2.setActive(true);

            List<DayPlan> days2 = new ArrayList<>();
            days2.add(makeDayPlan(t2, 1, "Arrival Kochi", "Fort Kochi walk, Chinese fishing nets"));
            days2.add(makeDayPlan(t2, 2, "Munnar Drive", "Tea museum, Eravikulam National Park"));
            days2.add(makeDayPlan(t2, 3, "Munnar Hills", "Top Station, tea garden trek"));
            days2.add(makeDayPlan(t2, 4, "Munnar to Alleppey", "Drive through rubber plantations"));
            days2.add(makeDayPlan(t2, 5, "Houseboat Day", "Full day backwater cruise and overnight stay"));
            days2.add(makeDayPlan(t2, 6, "Departure", "Village walk and airport drop"));
            t2.setDayPlans(days2);

            Itinerary t3 = new Itinerary();
            t3.setTitle("Bali Island Escape");
            t3.setDestination("Bali, Indonesia");
            t3.setTourType("INTERNATIONAL");
            t3.setDescription("Discover Bali's soul through ancient temples, emerald rice terraces, sacred ceremonies, and sun-drenched beaches.");
            t3.setDuration(8);
            t3.setPrice(75000);
            t3.setImageUrl("https://images.unsplash.com/photo-1537996194471-e657df975ab4?w=800");
            t3.setHighlights(Arrays.asList("Ubud Monkey Forest", "Tanah Lot Temple", "Rice Terrace Trek", "Seminyak Beach"));
            t3.setActive(true);

            List<DayPlan> days3 = new ArrayList<>();
            days3.add(makeDayPlan(t3, 1, "Arrival Denpasar", "Transfer to Ubud, welcome dinner"));
            days3.add(makeDayPlan(t3, 2, "Ubud Culture", "Monkey Forest, Royal Palace, Art Market"));
            days3.add(makeDayPlan(t3, 3, "Rice Terraces", "Tegallalang, Tirta Empul Temple"));
            days3.add(makeDayPlan(t3, 4, "North Bali", "Sekumpul Waterfall, Ulun Danu Temple"));
            days3.add(makeDayPlan(t3, 5, "Kintamani", "Mount Batur sunrise hike option"));
            days3.add(makeDayPlan(t3, 6, "Tanah Lot", "Tanah Lot temple, Seminyak beach"));
            days3.add(makeDayPlan(t3, 7, "Uluwatu", "Clifftop temple, Kecak fire dance"));
            days3.add(makeDayPlan(t3, 8, "Departure", "Spa morning and airport transfer"));
            t3.setDayPlans(days3);

            Itinerary t4 = new Itinerary();
            t4.setTitle("Dubai and Abu Dhabi Luxury");
            t4.setDestination("Dubai and Abu Dhabi, UAE");
            t4.setTourType("INTERNATIONAL");
            t4.setDescription("Experience the ultimate in Arabian luxury with towering skylines, golden deserts, and world-class hospitality.");
            t4.setDuration(6);
            t4.setPrice(95000);
            t4.setImageUrl("https://images.unsplash.com/photo-1512453979798-5ea266f8880c?w=800");
            t4.setHighlights(Arrays.asList("Burj Khalifa", "Desert Safari", "Sheikh Zayed Mosque", "Dubai Mall"));
            t4.setActive(true);

            List<DayPlan> days4 = new ArrayList<>();
            days4.add(makeDayPlan(t4, 1, "Arrival Dubai", "Hotel check-in, Dubai Marina walk"));
            days4.add(makeDayPlan(t4, 2, "Old and New Dubai", "Gold Souk, Spice Souk, Burj Khalifa"));
            days4.add(makeDayPlan(t4, 3, "Desert Safari", "Dune bashing, camel ride, BBQ dinner"));
            days4.add(makeDayPlan(t4, 4, "Abu Dhabi Day", "Sheikh Zayed Mosque, Louvre Abu Dhabi"));
            days4.add(makeDayPlan(t4, 5, "Dubai Thrills", "Ski Dubai, Palm Jumeirah, Atlantis"));
            days4.add(makeDayPlan(t4, 6, "Departure", "Last shopping and airport transfer"));
            t4.setDayPlans(days4);

            itineraryRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
            System.out.println("Sample itineraries created");
        }
    }

    private DayPlan makeDayPlan(Itinerary it, int day, String title, String desc) {
        DayPlan dp = new DayPlan();
        dp.setItinerary(it);
        dp.setDay(day);
        dp.setTitle(title);
        dp.setDescription(desc);
        return dp;
    }
}
