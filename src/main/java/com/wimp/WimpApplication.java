package com.wimp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class })
@EnableRedisHttpSession
public class WimpApplication {
	public static void main(String[] args) {
		SpringApplication.run(WimpApplication.class, args);
	}

}
