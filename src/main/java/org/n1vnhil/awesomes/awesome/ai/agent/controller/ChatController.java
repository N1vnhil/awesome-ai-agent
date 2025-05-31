package org.n1vnhil.awesomes.awesome.ai.agent.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.n1vnhil.awesomes.awesome.ai.agent.app.LoveApp;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/chat")
public class ChatController {

    @Resource
    LoveApp loveApp;

    @PostMapping
    public String memoryChat(@RequestBody String message) {
        return loveApp.doChat(message, "0");
    }

    @PostMapping("/question")
    public String questionChat(@RequestBody String message) {
        return loveApp.doChatWithRag(message, "0");
    }

    @PostMapping("/question/cloud")
    public String questionChatWithCloudRag(@RequestBody String message) {
        return loveApp.doChatWithCloudRag(message, "0");
    }
}
