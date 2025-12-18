package com.bank.aiservice.agent;

import com.bank.aiservice.llm.LlmClient;
import com.bank.aiservice.memory.ConversationMemory;
import com.bank.aiservice.memory.ConversationMemoryStore;

import org.springframework.stereotype.Service;

@Service
public class SupportAiAgent {

    private final LlmClient llmClient;
    private final ConversationMemoryStore memoryStore;

    public SupportAiAgent(LlmClient llmClient,ConversationMemoryStore memoryStore) {
        this.llmClient = llmClient;
        this.memoryStore = memoryStore;
    }


    public String answer(String sessionId, String question) {

        ConversationMemory memory = memoryStore.getMemory(sessionId);

        memory.addUserMessage(question);

        String prompt = buildPrompt(memory);

        String aiResponse = llmClient.call(prompt);

        memory.addAiMessage(aiResponse);

        return aiResponse;
    }
    
    
    private String buildPrompt(ConversationMemory memory) {

        return """
				You are a Customer Support AI Agent for Acme Corp.
				
				FAQ:
				- Working hours: 9 AM to 6 PM, Monday to Friday
				- Support email: support@acme.com
				- Refund policy: Refunds available within 7 days of purchase
				
				Rules:
				- Answer ONLY using the FAQ above
				- Use EXACT wording from the FAQ
				- If unknown, say: "I'm sorry, I don't have that information."
				
				Conversation so far:
				""" + memory.asPromptContext() + """
				
				Now answer the user's latest question.
				""";
        
    }
    
    
    public String answer(String question) {

        String prompt = """
        You are a Customer Support AI Agent for Acme Corp.

        FAQ:
        - Working hours: 9 AM to 6 PM, Monday to Friday
        - Support email: support@acme.com
        - Refund policy: 7 days from the date of purchase

        Rules:
        - Answer ONLY using the FAQ above
        - rephrase the FAQ information but don't change Time/Number to answer the question
        - If the answer is not in FAQ, say:
          "I'm sorry, I don't have that information."
        - Do not guess
        - Do not hallucinate
        
        
        Output Format (STRICT JSON ONLY):
		{
		  "answer": "<string>",
		  "confidence": "HIGH | LOW"
		}
		
		If the answer exists in the FAQ:
		- confidence = HIGH
		If the answer does not exist:
		- answer = "I'm sorry, I don't have that information."
		- confidence = LOW

        User Question:
        """ + question;

        return llmClient.call(prompt);
    }
}

