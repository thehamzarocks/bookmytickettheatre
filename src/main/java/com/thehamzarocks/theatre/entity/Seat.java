package com.thehamzarocks.theatre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Seat {

  @Id @GeneratedValue Long id;

  @JsonBackReference(value = "booking-seat") @ManyToOne
  Booking booking;

  private String name;

  private String status;

  @ManyToOne @JsonBackReference(value = "theatreshow-seat") private TheatreShow theatreShow;

  public Seat() {}

  public Seat(Booking booking, String name, String status, TheatreShow theatreShow) {
    this.booking = booking;
    this.name = name;
    this.status = status;
    this.theatreShow = theatreShow;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Booking getBooking() {
    return booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public TheatreShow getTheatreShow() {
    return theatreShow;
  }

  public void setTheatreShow(TheatreShow theatreShow) {
    this.theatreShow = theatreShow;
  }

  @Override
  public String toString() {
    return "Seat{" + "id=" + id + ", name='" + name + '\'' + ", status='" + status + '\'' + '}';
  }
}
