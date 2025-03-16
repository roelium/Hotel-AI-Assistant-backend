package com.roelproject.RoelHotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.roelproject.RoelHotel.langchain4j.LangChain4jAssistant;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private final LangChain4jAssistant langChain4JAssistant;

    public ChatController(LangChain4jAssistant langChain4JAssistant) {
        this.langChain4JAssistant = langChain4JAssistant;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("chatid")  String chatId, @RequestParam("message")  String userMessage) {
        return langChain4JAssistant.chat(chatId, userMessage);
    }
}