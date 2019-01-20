package com.example.jaxws.bo;

import org.springframework.stereotype.Component;

@Component
public class GreetingBOImpl implements GreetingBO{
	public String greeting() {
		return "Welcome to Spring JAX-WS integration";
	}
}
