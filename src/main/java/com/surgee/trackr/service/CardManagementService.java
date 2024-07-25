package com.surgee.trackr.service;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class CardManagementService {
    private final RestTemplate restTemplate;

    public String exchangeToken(String token) {
        System.out.println(token);

        Object response =  restTemplate.postForObject(
            "https://api.withmono.com/v2/accounts/auth", 
            token, 
            String.class);

            System.out.println(response);

            return "ok";

    }

}
