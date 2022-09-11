package com.example.hw_institutionCatalog;

import com.example.hw_institutionCatalog.dto.in.DeleteOwnerInDto;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.service.InstitutionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitDeleteOwner {
    private final InstitutionService institutionService;

    public RabbitDeleteOwner(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RabbitListener(queues = "myQueueDeleteOwner")
    private void rabbit(@Payload DeleteOwnerInDto deleteOwnerInDto) throws InstitutionNotFoundException {
        System.out.println("delete user " + "deleteOwnerInDto");
        institutionService.deleteOwner(deleteOwnerInDto);
    }
}
