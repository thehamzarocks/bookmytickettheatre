package com.thehamzarocks.theatre;

import com.thehamzarocks.theatre.dto.BookShowRequest;
import com.thehamzarocks.theatre.entity.Booking;
import com.thehamzarocks.theatre.entity.Seat;
import com.thehamzarocks.theatre.entity.TheatreShow;
import com.thehamzarocks.theatre.repository.BookingRepository;
import com.thehamzarocks.theatre.repository.SeatRepository;
import com.thehamzarocks.theatre.repository.TheatreShowRepository;
import com.thehamzarocks.theatre.service.TheatreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.assertEquals;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookShowTest {

  @InjectMocks
  TheatreService theatreService;

  @Mock
  BookingRepository bookingRepository;
  @Mock
  TheatreShowRepository theatreShowRepository;

  @Mock
  SeatRepository seatRepository;

  @Mock RestTemplate restTemplate;

  @Test
  public void bookShowTooManySeats() {
    when(theatreShowRepository.findById(any())).thenReturn(Optional.of(new TheatreShow()));
    String response =
        theatreService.bookShow(
            5L,
            new BookShowRequest(
                5L, "Jill", List.of(10L, 312L, 55L, 424L, 3424L, 234234L, 34L), false));
    assertEquals("Cannot book more than 6 seats at a time", response);
  }

  @Test
  public void testRequestedSeatsNotAvailable() {
    List<Seat> availableSeats = new ArrayList<>();
    Seat seat1 = new Seat(new Booking(), "5Q", "unassigned", new TheatreShow());
    seat1.setId(5L);
    Seat seat2 = new Seat(new Booking(), "10M", "occupied", new TheatreShow());
    seat2.setId(10L);
    availableSeats.add(seat1);
    availableSeats.add(seat2);

    when(theatreShowRepository.findById(any()))
        .thenReturn(
            Optional.of(
                new TheatreShow(
                    "morning",
                    new GregorianCalendar(2021, Calendar.MAY, 28).getTime(),
                    5L,
                    7L,
                    availableSeats)));
    String response =
        theatreService.bookShow(5L, new BookShowRequest(5L, "Jill", List.of(10L, 312L), false));
    assertEquals("Requested seats not available", response);
  }
}
