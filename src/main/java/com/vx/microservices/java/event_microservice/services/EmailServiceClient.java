package com.vx.microservices.java.event_microservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/* Classe responsável por consumir o meu serviço de Emails */
/* É UMA INTERFACE PORQUE NÃO VAI TER IMPLEMENTAÇÃO, SOMENTE DECLARAÇÃO, quem faz a
* implementação é o feign por debaixo dos panos */

/* estou declarando um nome e a url da onde está rodando o nosso email service,
* que no caso vai ser a aplicação do desafio da uber*/
@FeignClient(name = "email-service", url = "http://endereco-do-servico-de-email")
public interface EmailServiceClient {

    /* Aqui estou declarando o método post e estou dizendo que esse método quando for chamado
    * ele tem que fazer um post na url declarada ali em cima e nesse endpoint "/send
    * passando essa informação no body."*/
    @PostMapping("/send")
    void sendEmail(@RequestBody EmailRequestDTO emailRequest);


}
