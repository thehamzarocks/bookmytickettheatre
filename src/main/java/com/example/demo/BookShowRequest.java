package com.example.demo;

import java.util.List;

public class BookShowRequest {
  Long id;
  List<Long> seatsToBook;
  Boolean forceBookAny;

  public BookShowRequest(Long id, List<Long> seatsToBook, Boolean forceBookAny) {
    this.id = id;
    this.seatsToBook = seatsToBook;
    this.forceBookAny = forceBookAny;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Long> getSeatsToBook() {
    return seatsToBook;
  }

  public void setSeatsToBook(List<Long> seatsToBook) {
    this.seatsToBook = seatsToBook;
  }

  public Boolean getForceBookAny() {
    return forceBookAny;
  }

  public void setForceBookAny(Boolean forceBookAny) {
    this.forceBookAny = forceBookAny;
  }
}

