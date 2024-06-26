package com.vx.microservices.java.event_microservice.domain;


import jakarta.persistence.*;
import lombok.*;

/*Declarando entidade que representa uma tabela no meu banco de dados*/

@Entity(name="event")
@Table(name="event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int maxParticipants;
    private int registeredParticipants;
    private String date;
    private String title;
    private String description;

}
