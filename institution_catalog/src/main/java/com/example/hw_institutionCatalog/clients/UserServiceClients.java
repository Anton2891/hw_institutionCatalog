package com.example.hw_institutionCatalog.clients;

import org.hibernate.mapping.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "userServiceClient", url = "${my.userServiceClient.url}", decode404 = true)
public interface UserServiceClients {

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}",
            consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> getUser(@PathVariable (value = "userId")Integer userId);

}
