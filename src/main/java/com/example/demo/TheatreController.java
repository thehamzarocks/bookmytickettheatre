package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class TheatreController {

  @Autowired
  TheatreShowRepository theatreShowRepository;

  @GetMapping("/show")
  public List<TheatreShow> getShowDetails() {
    return theatreShowRepository.findAll();
  }

}
