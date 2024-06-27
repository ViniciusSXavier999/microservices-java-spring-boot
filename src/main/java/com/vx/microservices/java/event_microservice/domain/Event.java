package com.vx.microservices.java.event_microservice.domain;


import com.vx.microservices.java.event_microservice.dtos.EventRequestDTO;
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

    /* convertendo dto para entidade */
    public Event(EventRequestDTO eventRequestDTO) {
        this.date = eventRequestDTO.date();
        this.maxParticipants = eventRequestDTO.maxParticipants();
        this.registeredParticipants = eventRequestDTO.registeredParticipants();
        this.title = eventRequestDTO.title();
        this.description = eventRequestDTO.description();
    }

}
