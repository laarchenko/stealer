package com.example.stealer.integration;

import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RandomBeansExtension.class)
public class AbstractIntegrationTest {

    @Autowired
    public MockMvc client;

    protected final String BASE_PATH = "/api/v1";
}
