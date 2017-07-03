package com.metflix;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class HomeController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${recommendation.api:http://localhost:3333}")
	private URI recommendationApi;


	@RequestMapping("/")
	public String home(Principal principal, Model model) {

		List<Movie> recommendations = this.restTemplate.exchange(
				RequestEntity.get(UriComponentsBuilder
								.fromUri(this.recommendationApi)
								.pathSegment("api", "recommendations", principal.getName())
								.build()
								.toUri())
						.build(),
				new ParameterizedTypeReference<List<Movie>>() {})
				.getBody();

		model.addAttribute(
				"username",
				principal.getName());
		model.addAttribute(
				"recommendations",
				recommendations);

		return "index";
	}
}
