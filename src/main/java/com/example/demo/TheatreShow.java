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
  private String movieName;
  private String theatre;

  @OneToMany(mappedBy = "theatreShow")
  @JsonManagedReference
  private List<Seat> seats;

  public TheatreShow() {}

  public TheatreShow(String time, String movieName, String theatre, List<Seat> seats) {
    this.time = time;
    this.movieName = movieName;
    this.theatre = theatre;
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

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }

  public String getTheatre() {
    return theatre;
  }

  public void setTheatre(String theatre) {
    this.theatre = theatre;
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public void setSeats(List<Seat> seats) {
    this.seats = seats;
  }
}
