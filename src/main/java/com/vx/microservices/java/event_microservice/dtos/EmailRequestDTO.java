package com.vx.microservices.java.event_microservice.dtos;

public record EmailRequestDTO(String to, String subject, String body) {
}
