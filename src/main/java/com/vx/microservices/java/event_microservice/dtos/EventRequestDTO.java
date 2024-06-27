package com.vx.microservices.java.event_microservice.dtos;

public record EventRequestDTO(int maxParticipants, int registeredParticipants,
                              String date, String title, String description) {
}
