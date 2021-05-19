package com.thehamzarocks.theatre.repository;

import com.thehamzarocks.theatre.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {}
