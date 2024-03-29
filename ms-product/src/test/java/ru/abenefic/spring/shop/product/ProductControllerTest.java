package ru.abenefic.spring.shop.product;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    private final MockMvc mockMvc;

    public ProductControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void getAllProductsTest() throws Exception {
        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].title", Matchers.equalTo("Book")));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        mockMvc.perform(get("/api/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", Matchers.equalTo("Book")));

    }

    @Test
    @Transactional
    @Rollback
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void addProductTest() throws Exception {
        String json = """
                {
                    "title": "Book",
                    "cost": 250.5,
                    "categoryName": "First",
                    "categoryId": 1
                }
                """;
        mockMvc.perform(post("/api/v1/products")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void addProductErrorTest() throws Exception {
        String json = """
                {
                    "title": "Book",
                    "cost": 250.5,
                    "categoryName": "First",
                    "categoryId": 1
                }
                """;
        mockMvc.perform(post("/api/v1/products")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

}
