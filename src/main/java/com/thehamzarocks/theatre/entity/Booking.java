package com.thehamzarocks.theatre.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Booking {
  @Id @GeneratedValue Long id;
  String userName;
  String OTP;
  @OneToOne
  TheatreShow theatreShow;

  @OneToMany @JsonManagedReference(value = "booking-seat") List<Seat> seats;

  public Booking() {}

  public Booking(String userName, String OTP, TheatreShow theatreShow) {
    this.userName = userName;
    this.OTP = OTP;
    this.theatreShow = theatreShow;
  }

  public void setSeats(List<Seat> seats) {
    this.seats = seats;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getOTP() {
    return OTP;
  }

  public void setOTP(String OTP) {
    this.OTP = OTP;
  }

  public TheatreShow getTheatreShow() {
    return theatreShow;
  }

  public void setTheatreShow(TheatreShow theatreShow) {
    this.theatreShow = theatreShow;
  }

  public List<Seat> getSeats() {
    return seats;
  }
}
