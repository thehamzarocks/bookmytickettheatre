package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheatreService {

  @Autowired
  TheatreShowRepository theatreShowRepository;

  @Autowired
  SeatRepository seatRepository;

  @Transactional
  public String bookShow(Long id, BookShowRequest bookShowRequest) {
    TheatreShow show = theatreShowRepository.findById(id).orElse(null);
    if(show == null) {
      return "Show not found";
    }
    List<Long> requestedSeats = bookShowRequest.getSeatsToBook();
    if(requestedSeats.size() > 6) {
      return "Cannot book more than 6 seats at a time";
    }
    List<Seat> availableSeats = show.getSeats().stream().filter(seat -> {
      return "unassigned".equals(seat.getStatus()) && requestedSeats.contains(seat.id);
    }).collect(Collectors.toList());
    if(availableSeats.size() < requestedSeats.size()) {
      return "Requested seats not available";
    }

    availableSeats.forEach(availableSeat -> {
      availableSeat.setStatus("occupied");
      seatRepository.save(availableSeat);
    });
    return "Booked!";
  }
}
