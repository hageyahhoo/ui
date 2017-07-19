package com.metflix;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class HomeControllerTestWithoutMockMvc {

	@MockBean
	private RestTemplate restTemplate;

	@InjectMocks
	private HomeController sut = new HomeController();


	@Before
	public void setUp() throws URISyntaxException {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(
				this.sut,
				"recommendationApi",
				new URI("http://localhost:3333"));
	}


	@Test
	public void home() throws Exception {

		List<Movie> movieList = new ArrayList<Movie>();
		movieList.add(new Movie("SAO"));

		Principal principal = new JMXPrincipal("a");
		Model     model     = new ExtendedModelMap();

		given(this.restTemplate.exchange(
					any(RequestEntity.class),
					eq(new ParameterizedTypeReference<List<Movie>>() {})))
				.willReturn(
						new ResponseEntity<List<Movie>>(
								movieList,
								HttpStatus.OK));

		assertThat(
				this.sut.home(principal, model),
				is("index"));

		Map<String, Object> resultMap = model.asMap();
		assertThat(
				resultMap.get("username"),
				is("a"));
		assertThat(
				resultMap.get("recommendations"),
				is(movieList));
	}
}
