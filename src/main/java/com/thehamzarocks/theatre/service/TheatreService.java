package com.thehamzarocks.theatre.service;

import com.thehamzarocks.theatre.entity.TheatreShow;
import com.thehamzarocks.theatre.repository.TheatreShowRepository;
import com.thehamzarocks.theatre.dto.BookShowRequest;
import com.thehamzarocks.theatre.entity.Booking;
import com.thehamzarocks.theatre.entity.Seat;
import com.thehamzarocks.theatre.repository.BookingRepository;
import com.thehamzarocks.theatre.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TheatreService {
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }

  @Autowired
  BookingRepository bookingRepository;
  @Autowired
  TheatreShowRepository theatreShowRepository;

  @Autowired
  SeatRepository seatRepository;

  @Autowired RestTemplate restTemplate;

  private static final int MAX_SEATS_ALLOWED = 6;

  @Transactional
  public String bookShow(Long id, BookShowRequest bookShowRequest) {
    TheatreShow show = theatreShowRepository.findById(id).orElse(null);
    if (show == null) {
      return "Show not found";
    }
    List<Long> requestedSeats = bookShowRequest.getSeatsToBook();
    if (requestedSeats.size() > MAX_SEATS_ALLOWED) {
      return String.format("Cannot book more than %s seats at a time", MAX_SEATS_ALLOWED);
    }
    List<Seat> availableSeats = getAvailableSeats(show, requestedSeats);
    if (availableSeats.size() < requestedSeats.size()) {
      return "Requested seats not available";
    }

    String otp = generateOtp();
    Booking booking = new Booking(bookShowRequest.getUserName(), otp, show);
    booking.setSeats(availableSeats);
    Booking booked = bookingRepository.save(booking);
    assignSeats(availableSeats, booked);

    return "Booked, OTP: " + otp;
  }

  public String addShowsToBookMyTicket(List<TheatreShow> addShowsRequest) {
    restTemplate.postForObject("http://localhost:8080/shows", addShowsRequest, String.class);
    return "Success!";
  }

  private void assignSeats(List<Seat> availableSeats, Booking booked) {
    availableSeats.forEach(
        availableSeat -> {
          availableSeat.setStatus("occupied");
          availableSeat.setBooking(booked);
          seatRepository.save(availableSeat);
        });
  }

  private List<Seat> getAvailableSeats(TheatreShow show, List<Long> requestedSeats) {
    return show.getSeats().stream()
        .filter(
            seat -> {
              return "unassigned".equals(seat.getStatus()) && requestedSeats.contains(seat.getId());
            })
        .collect(Collectors.toList());
  }

  private String generateOtp() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    String generatedString =
        random
            .ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    return generatedString;
  }
}
