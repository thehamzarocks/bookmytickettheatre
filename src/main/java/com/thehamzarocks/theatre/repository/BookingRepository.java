package com.thehamzarocks.theatre.repository;

import com.thehamzarocks.theatre.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {}
