package com.vx.microservices.java.event_microservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="subscription")
@Table(name="subscription")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*Representando uma relação */
    // relação de muitos para um
    /* muitas inscriçoes para um evento  */
    @ManyToOne
    private Event event;

    private String participantEmail;

}
