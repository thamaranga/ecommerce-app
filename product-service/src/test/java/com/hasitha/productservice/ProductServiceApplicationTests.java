package com.hasitha.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hasitha.productservice.dto.ProductRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Autowired
	MockMvc mockMvc;

	//Converts pojo object to  string / string to pojo object
	@Autowired
	ObjectMapper objectMapper;


	//Required mongo db dokcer container is mentioned as below.
	@Container
	static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo:4.4.2");

	//Get the mongodb  url from docker image and set to spring.data.mongodb.uri property.
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);

	}


	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequestDTO productRequestDTO=getProductRequest();
		String productRequestString=objectMapper.writeValueAsString(productRequestDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString)).andExpect(status().isCreated());
	}

	private ProductRequestDTO getProductRequest() {
		return ProductRequestDTO.builder()
				.name("NIke Air")
				.description("Mens Shoes")
				.price(BigDecimal.valueOf(250))
				.build();
	}

}*/
