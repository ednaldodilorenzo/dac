package br.edu.ifpb.sr.dac.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hello")
public class HelloWorldController {

    @GetMapping
    public String getHello(@AuthenticationPrincipal Jwt jwt) {
        System.out.println("Hello World " + jwt.getSubject());
        return "Olá";
    }
}
