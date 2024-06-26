package com.vx.microservices.java.event_microservice.repositories;

import com.vx.microservices.java.event_microservice.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
