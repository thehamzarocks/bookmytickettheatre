package com.thehamzarocks.theatre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @PostMapping("/booking/{id}")
  public String bookShow(@PathVariable("id") Long showId, @RequestBody BookShowRequest bookShowRequest) {
    return theatreService.bookShow(showId, bookShowRequest);
  }

  @PostMapping("/shows")
  public String addShows(@RequestBody List<TheatreShow> addShowsRequest) {
    return theatreService.addShowsToBookMyTicket(addShowsRequest);
  }

}
