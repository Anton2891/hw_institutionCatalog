package com.example.hw_institutionCatalog;

import com.example.hw_institutionCatalog.dto.in.ChangeOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.DeleteOwnerInDto;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.service.InstitutionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitChangeOwner {
    private final InstitutionService institutionService;

    public RabbitChangeOwner(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RabbitListener(queues = "myQueue")
    private void rabbit(@Payload ChangeOwnerInDto changeOwnerInDto) throws InstitutionNotFoundException {
        System.out.println("change user " + "deleteOwnerInDto");
        institutionService.changeOwner(changeOwnerInDto);
    }
}
