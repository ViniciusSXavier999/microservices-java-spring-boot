package com.vx.microservices.java.event_microservice.services;

import com.vx.microservices.java.event_microservice.domain.Event;
import com.vx.microservices.java.event_microservice.domain.Subscription;
import com.vx.microservices.java.event_microservice.dtos.EmailRequestDTO;
import com.vx.microservices.java.event_microservice.dtos.EventRequestDTO;
import com.vx.microservices.java.event_microservice.repositories.EventRepository;
import com.vx.microservices.java.event_microservice.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EventService {

    /* Estou pegando o eventRepository e o subscriptionRepository para poder
    * manipular as tabelas lá no banco de dados, assim será possivel
    * 1 -> Cadastrar novo evento
    * 2 -> Cadastrar nova inscrição
    * */

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailServiceClient emailServiceClient;

    @Autowired
    private EventRepository eventRepository;

    // MÉTODO PARA LISTAR TODOS OS EVENTOS DA TABELA
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // MÉTODO PARA PEGAR EVENTOS PRÓXIMOS

    /* Estou pegando o método que declarei no eventRepository e pegando a data de agora
    * através do LocalDateTime.now() entre parametros */
    public List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    // MÉTODO PARA CRIAR UM NOVO EVENTO

    /* eu vou receber esse evento no body da requisição
    * e ai vou ter que criar uma nova entidade do tipo Event e persistir essa
    * entidade na tabela usando o meu repository */
    public Event createEvent(EventRequestDTO eventRequest){
        Event newEvent = new Event(eventRequest);
        return eventRepository.save(newEvent);
    }

    // MÉTODO PARA REGISTRAR UMA NOVO PARTICIPANTE NO EVENTO
    public void registerParticipant(String eventId, String participantEmail){
        /* primeira coisa que vou fazer é buscar o evento pelo id, se eu não encontrar
        * vou lançar uma exceção */
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);

        /* Se encontramos o evento então podemos criar a inscrição*/

        if (event.getRegisteredParticipants() < event.getMaxParticipants()){
            Subscription subscription = new Subscription(event, participantEmail);
            subscriptionRepository.save(subscription);

            /* Aqui estou pegando os registrantes e fazendo + 1 para atualizar o valor
            * de participantes registrados no banco de dados*/
            event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);

            // agora vou enviar o email
            EmailRequestDTO emailRequest = new EmailRequestDTO(participantEmail,
                    "Confirmação de Inscrição",
                    "Você foi inscrito no evento com sucesso!");

            /* para esse método "sendEmail" a gente tinha declarado que ele recebe por
            * parametro um EmailRequestDTO então eu tive que criar esse EmailRequestDTO
            * logo acima e passando os devidos valores pelo parametro. */
            emailServiceClient.sendEmail(emailRequest);
        } else {
            throw new EventFullException();
        }
    }


}
