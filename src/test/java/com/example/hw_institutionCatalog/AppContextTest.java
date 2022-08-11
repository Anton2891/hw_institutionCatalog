package com.example.hw_institutionCatalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
