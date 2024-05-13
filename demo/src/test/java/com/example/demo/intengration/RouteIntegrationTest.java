package com.example.demo.intengration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyString;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class RouteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    //Get integration tests
    @Test
    public void testRouteReturnsAllProducts() throws Exception {
        this.mockMvc.perform(get("/product/allProducts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(not(isEmptyString())));
    }
    //Get allProducts
    @Test
    public void testRouteReturnsHighestProductsPrice() throws Exception {
        this.mockMvc.perform(get("/product/HighstUnitInStock")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(not(isEmptyString())));
    }

    //teste verificar se retorna código  200
    @Test
    public void testRouteGetReturnsOk() throws Exception {
        this.mockMvc.perform(get("/product/allProducts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testRoutePostReturnsOk() throws Exception {
        String jsonContent = "{\"product_name\": \"Test Product\", \"price\": 10.0, \"stock\": 100}";

        // Simula uma solicitação POST para /product/addProduct
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/product/addProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
    






