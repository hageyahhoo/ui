package com.metflix;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private RestTemplate restTemplate;


	@Test
	public void home() throws Exception {

		given(this.restTemplate.exchange(any(RequestEntity.class), eq(ParameterizedTypeReference.class)))
				.willReturn(new ResponseEntity<ParameterizedTypeReference>(new ParameterizedTypeReference<List<Movie>>() {}, HttpStatus.OK));

		this.mvc.perform(MockMvcRequestBuilders.post("/").accept(MediaType.APPLICATION_JSON))
				// FIXME 403で良いのか？
				.andExpect(status().isForbidden())
				// FIXME "index"を検証しなくて良いのか？
				.andExpect(content().string(equalTo("")));
//				.andExpect(content().string(equalTo("index")));

		// TODO Modelの検証
	}
}
