package com.thehamzarocks.theatre.configuration;

import com.thehamzarocks.theatre.entity.Seat;
import com.thehamzarocks.theatre.repository.SeatRepository;
import com.thehamzarocks.theatre.entity.TheatreShow;
import com.thehamzarocks.theatre.repository.TheatreShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.IntStream;

@Configuration
public class LoadDatabase {

  @Autowired
  TheatreShowRepository theatreShowRepository;

  @Autowired
  SeatRepository seatRepository;

  @Transactional
  public void createShowSeats() {
    TheatreShow theBigShortNoonShow = new TheatreShow("noon", new GregorianCalendar(2021, Calendar.MAY, 25).getTime(), 4L, 6L, new ArrayList<>());
    theatreShowRepository.save(theBigShortNoonShow);

    TheatreShow timeMovieNoonShow = new TheatreShow("noon", new GregorianCalendar(2021, Calendar.MAY, 25).getTime(), 4L, 7L, new ArrayList<>());
    theatreShowRepository.save(timeMovieNoonShow);

    IntStream.of(0, 1, 2).forEach(value -> {
      Seat seat = new Seat(null, value + "A", "unassigned", theBigShortNoonShow);
      seatRepository.save(seat);
      theBigShortNoonShow.getSeats().add(seat);
    });

    List<Seat> timeMovieSeats = new ArrayList<>();
    IntStream.of(0, 1, 2, 3, 4).forEach(value -> {
      Seat seat = new Seat(null, value + "Q", "unassigned", timeMovieNoonShow);
      seatRepository.save(seat);
      timeMovieNoonShow.getSeats().add(seat);
    });
  }

  @Bean
  CommandLineRunner initDatabase() {
    return args -> {
      createShowSeats();
    };
  }
}
