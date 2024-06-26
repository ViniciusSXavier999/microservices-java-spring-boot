package com.vx.microservices.java.event_microservice.repositories;

import com.vx.microservices.java.event_microservice.domain.Event;
import feign.Param;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, String> {



    @Query(value = "SELECT * FROM events e WHERE parsedatetime(e.date, 'dd/MM/yyyy') > :currentDate", nativeQuery = true)
    // encontre eventos que vão acontecer no futuro
    List<Event> findUpcomingEvents(@Param ("currentDate") LocalDateTime currentDate);

    @Nonnull
    Optional<Event> findById(@Nonnull String id);

}
