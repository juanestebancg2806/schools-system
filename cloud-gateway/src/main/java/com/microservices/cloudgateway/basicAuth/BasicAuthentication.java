package com.microservices.cloudgateway.basicAuth;


import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

/*
https://effortlesscodelearning.com/blog/how-to-implement-basic-authentication-with-spring-cloud-gateway/

Since spring cloud gateway is reactive application, we can use ReactiveAuthenticationManager interface.
This interface is responsible for authenticating a user based on the provided credentials. It is used in reactive(non-blocking) applications,
for non-reactive(blocking) application AuthenticationManager interface is used. It is a key component in the authentication process.

*/

@Component
public class BasicAuthentication implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        System.out.println(authentication);
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(name.equalsIgnoreCase("admin") && password.equals("admin")){
            return Mono.just(new UsernamePasswordAuthenticationToken(
                    name,password, Collections.emptyList()));
        }
        return null;
    }
}
