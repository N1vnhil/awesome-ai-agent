package org.n1vnhil.awesomes.awesome.ai.agent.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import lombok.extern.slf4j.Slf4j;
import org.n1vnhil.awesomes.awesome.ai.agent.advisors.LogAdvisor;
import org.n1vnhil.awesomes.awesome.ai.agent.advisors.Re2Advisor;
import org.n1vnhil.awesomes.awesome.ai.agent.chatmemory.FileBasedChatMemory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.n1vnhil.awesomes.awesome.ai.agent.constants.SystemPromptConstants.SYSTEM_PROMPT;

@Slf4j
@Configuration
public class PaperWritingChatClientConfig {

    @Bean
    public ChatClient dashScopeChatClient() {
        String saveDir = System.getProperty("user.dir") + "/chat-memory";
        return ChatClient.builder(new DashScopeChatModel(new DashScopeApi(System.getenv("BAILIAN_API_KEY"))))
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(new FileBasedChatMemory(saveDir)),
                        new LogAdvisor(),
                        new Re2Advisor()
                )
                .defaultOptions(DashScopeChatOptions.builder().withTopP(0.7).build())
                .build();
    }

}
