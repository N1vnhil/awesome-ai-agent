package org.n1vnhil.awesomes.awesome.ai.agent.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ChatClient dashscopeChatClient;

    @Autowired
    private ChatModel ollamaChatModel;

    @GetMapping
    public String test() {
        return "ok";
    }

    @GetMapping("/dashscope/chat")
    public String dashscopeChat() {
        return dashscopeChatClient.prompt("你好吗？").call().content();
    }

    @GetMapping("/ollama/chat")
    public String ollamaChat() {
        return ollamaChatModel.call(new Prompt("你好吗？"))
                .getResult()
                .getOutput()
                .getText();
    }



}
