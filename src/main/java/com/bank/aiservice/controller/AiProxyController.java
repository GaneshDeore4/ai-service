package com.bank.aiservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/chat")
    public ResponseEntity<?> forwardToPython(@RequestBody Map<String, String> request) {

        String pythonUrl = "http://localhost:8000/ai/chat";

        ResponseEntity<Object> response =
                restTemplate.postForEntity(pythonUrl, request, Object.class);

        return ResponseEntity.ok(response.getBody());
    }
}

