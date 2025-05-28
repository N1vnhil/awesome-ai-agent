package org.n1vnhil.awesomes.awesome.ai.agent.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient dashScopeChatClient() {
        return ChatClient.builder(new DashScopeChatModel(new DashScopeApi(System.getenv("BAILIAN_API_KEY"))))
                .defaultSystem("你是一个Java高级开发工程师")
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultOptions(DashScopeChatOptions.builder().withTopP(0.7).build())
                .build();
    }

}
