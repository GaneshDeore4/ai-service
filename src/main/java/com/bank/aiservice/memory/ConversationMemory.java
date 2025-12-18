package com.bank.aiservice.memory;

import java.util.ArrayList;
import java.util.List;

public class ConversationMemory {

    private final List<String> messages = new ArrayList<>();

    public void addUserMessage(String message) {
        messages.add("User: " + message);
    }

    public void addAiMessage(String message) {
        messages.add("AI: " + message);
    }

    public String asPromptContext() {
        return String.join("\n", messages);
    }

    public int size() {
        return messages.size();
    }
}
