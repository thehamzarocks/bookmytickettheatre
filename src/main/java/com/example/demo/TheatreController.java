package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class TheatreController {

  @Autowired
  TheatreShowRepository theatreShowRepository;

  @Autowired
  TheatreService theatreService;

  @GetMapping("/show")
  public List<TheatreShow> getShowDetails() {
    return theatreShowRepository.findAll();
  }

  @GetMapping("/show/{id}")
  public TheatreShow getShowDetails(@PathVariable("id") Long showId) {
    return theatreShowRepository.findById(showId).orElseThrow();
  }

  @PostMapping("/show/{id}")
  public String bookShow(@PathVariable("id") Long showId, @RequestBody BookShowRequest bookShowRequest) {
    return theatreService.bookShow(showId, bookShowRequest);
  }

}
