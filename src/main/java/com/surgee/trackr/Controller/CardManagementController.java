package com.surgee.trackr.Controller;

import com.surgee.trackr.service.CardManagementService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mono")
@RequiredArgsConstructor
public class CardManagementController {
    private final CardManagementService cardManagementService;
    @PostMapping("/webhook")
    public String webhook(@RequestBody String payload) {
        System.out.println(payload);
        return "Webhook receiv";
    }

    @PostMapping("/exchange-token") 
    public String confirmUserAccount(@RequestBody String payload) {
        System.out.println(payload);
        return cardManagementService.exchangeToken(payload.toString());
    }
}
