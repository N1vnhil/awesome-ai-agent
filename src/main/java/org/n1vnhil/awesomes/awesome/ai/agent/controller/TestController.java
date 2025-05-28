package org.n1vnhil.awesomes.awesome.ai.agent.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ChatClient dashScopeChatClient;

    @GetMapping
    public String test() {
        return "ok";
    }

    @GetMapping("/chat")
    public String chat() {
        return dashScopeChatClient.prompt("你好吗？").call().content();
    }

}
