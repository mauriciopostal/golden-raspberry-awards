package com.mopsoftware.goldenraspberryawards.movie.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Teste da lista de produtores com maior e menor intervalo entre premiações")
    void testProducerIntervalResponse() throws Exception {
        mockMvc.perform(get("/v1/producers/award"))
                .andExpect(status().isOk())
                .andExpectAll(result -> {
                    String responseJson = result.getResponse().getContentAsString();

                    ObjectMapper mapper = new ObjectMapper();
                    ProducerAwardResponse actual = mapper.readValue(responseJson, ProducerAwardResponse.class);

                    assertEquals(actual.max().size(), 1);
                    assertEquals(actual.max().getFirst().interval(), 13);
                    assertEquals(actual.max().getFirst().producer(), "Matthew Vaughn");
                    assertEquals(actual.max().getFirst().previousWin(), 2002);
                    assertEquals(actual.max().getFirst().followingWin(), 2015);

                    assertEquals(actual.min().size(), 1);
                    assertEquals(actual.min().getFirst().interval(), 1);
                    assertEquals(actual.min().getFirst().producer(), "Joel Silver");
                    assertEquals(actual.min().getFirst().previousWin(), 1990);
                    assertEquals(actual.min().getFirst().followingWin(), 1991);
                });
    }
}
