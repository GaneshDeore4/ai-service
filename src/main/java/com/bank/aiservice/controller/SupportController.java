package com.bank.aiservice.controller;
import com.bank.aiservice.agent.SupportAiAgent;
import com.bank.aiservice.dto.QuestionRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai/support")
public class SupportController {

    private final SupportAiAgent agent;

    public SupportController(SupportAiAgent agent) {
        this.agent = agent;
    }

//    @PostMapping
//    public String ask(@RequestBody @Valid QuestionRequest request) {
//        return agent.answer(request.getQuestion());
//    }
    
    @PostMapping
    public String ask(
    		@RequestHeader("X-Session-Id") String sessionId,
    		@RequestBody @Valid QuestionRequest request) {
    	
        return agent.answer(sessionId, request.getQuestion());
    }

}
