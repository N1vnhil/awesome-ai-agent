package org.n1vnhil.awesomes.awesome.ai.agent.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ChatClient dashscopeChatClient;

    @GetMapping
    public String test() {
        return "ok";
    }

    @GetMapping("/dashscope/chat/{message}")
    public String dashscopeChat(@PathVariable String message) {
        return dashscopeChatClient.prompt(message).call().content();
    }


}
