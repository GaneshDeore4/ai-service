package com.bank.aiservice.memory;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConversationMemoryStore {

    private final Map<String, ConversationMemory> memoryMap = new ConcurrentHashMap<>();

    public ConversationMemory getMemory(String sessionId) {
    	
        return memoryMap.computeIfAbsent(sessionId, id -> new ConversationMemory());
    }
}
