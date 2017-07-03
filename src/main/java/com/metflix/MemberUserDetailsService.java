package com.metflix;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MemberUserDetailsService implements UserDetailsService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${member.api:http://localhost:4444}")
	private URI memberApi;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String member = this.restTemplate.exchange(
				RequestEntity.get(UriComponentsBuilder
								.fromUri(this.memberApi)
								.pathSegment("api", "members", username)
								.build()
								.toUri())
						.build(),
				String.class)
				.getBody();

		if (member == null) {
			throw new UsernameNotFoundException(username + " is not found");
		}

		return new User(
				username,
				"metflix",
				AuthorityUtils.createAuthorityList("MEMBER"));
	}
}
