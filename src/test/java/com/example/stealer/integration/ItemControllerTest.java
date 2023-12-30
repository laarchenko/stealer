package com.example.stealer.integration;

import com.example.stealer.dto.request.CreateItemRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.example.stealer.utils.MappingUtils.OBJECT_MAPPER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemControllerTest extends AbstractIntegrationTest {

    @Test
    @SneakyThrows
    void whenCreateItem_ItemIsCreated() {

        var request = CreateItemRequest.builder()
                .url("https://www.dollskill.com/smth")
                .userId(1L)
                .build();
        client.perform(post(BASE_PATH + "/item").content(OBJECT_MAPPER.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
