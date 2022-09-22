package com.example.hw_institutionCatalog;

import com.example.hw_institutionCatalog.clients.UserServiceClients;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {
        InstitutionApp.class,
        H2TestProfileJPAConfig.class
})
@ActiveProfiles("test")
public class AppContextTest {

    @Test
    void contextLoads() {
    }



}
