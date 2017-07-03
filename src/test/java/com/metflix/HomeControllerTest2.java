package com.metflix;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

import java.security.Principal;
import java.util.List;

import javax.management.remote.JMXPrincipal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class HomeControllerTest2 {

	@MockBean
	private RestTemplate restTemplate;

	@InjectMocks
	private HomeController sut = new HomeController();


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void home() throws Exception {

		given(this.restTemplate.exchange(any(RequestEntity.class), eq(ParameterizedTypeReference.class)))
				.willReturn(new ResponseEntity<ParameterizedTypeReference>(new ParameterizedTypeReference<List<Movie>>() {}, HttpStatus.OK));

		Principal principal = new JMXPrincipal("a");
		Model model = new ExtendedModelMap();
		System.out.println(sut.home(principal, model));
		// TODO Modelの検証
	}
}
