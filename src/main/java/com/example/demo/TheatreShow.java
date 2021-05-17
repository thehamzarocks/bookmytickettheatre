package com.example.demo;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class TheatreShow {

  @Id @GeneratedValue private Long id;

  private String time;
  private Long theatreId;
  private Long movieId;

  @OneToMany(mappedBy = "theatreShow")
  @JsonManagedReference
  private List<Seat> seats;

  public TheatreShow() {}

  public TheatreShow(String time, Long theatreId, Long movieId, List<Seat> seats) {
    this.time = time;
    this.theatreId = theatreId;
    this.movieId = movieId;
    this.seats = seats;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Long getMovieId() {
    return movieId;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public Long getTheatreId() {
    return theatreId;
  }

  public void setTheatreId(Long theatreId) {
    this.theatreId = theatreId;
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public void setSeats(List<Seat> seats) {
    this.seats = seats;
  }
}
