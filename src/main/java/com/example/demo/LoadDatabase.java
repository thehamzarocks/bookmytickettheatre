package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class LoadDatabase {

  @Autowired TheatreShowRepository theatreShowRepository;

  @Autowired SeatRepository seatRepository;

  @Transactional
  public void createShowSeats() {
    TheatreShow theatreShow = new TheatreShow("noon", "The Big Short", "Nice Movies Theatre", new ArrayList<>());
    theatreShowRepository.save(theatreShow);

    List<Seat> seats = new ArrayList<>();
    IntStream.of(0, 1, 2).forEach(value -> {
      Seat seat = new Seat(value + "A", "unassigned", theatreShow);
      seatRepository.save(seat);
      theatreShow.getSeats().add(seat);
    });
  }

  @Bean
  CommandLineRunner initDatabase() {
    return args -> {
      createShowSeats();
    };
  }
}