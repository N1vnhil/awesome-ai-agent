package org.n1vnhil.awesomes.awesome.ai.agent.app;

import io.swagger.v3.core.filter.SpecFilter;
import jakarta.annotation.Resource;
import org.n1vnhil.awesomes.awesome.ai.agent.advisors.LogAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoveApp {

    @Resource
    private ChatClient dashscopeChatClient;

    @Resource
    private VectorStore loveVectorStore;

    public String doChat(String message, String chatId) {
        ChatResponse chatResponse = dashscopeChatClient.prompt()
                .advisors(spec -> spec
                        .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .user(message)
                .call()
                .chatResponse();
        String responseMessage = chatResponse.getResult().getOutput().getText();
        return responseMessage;
    }

    public String doChatWithRag(String message, String chatId) {
        ChatResponse chatResponse = dashscopeChatClient.prompt()
                .user(message)
                .advisors(spec -> spec
                        .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .advisors(new LogAdvisor())
                .advisors(new QuestionAnswerAdvisor(loveVectorStore))
                .call().chatResponse();
        String responseMessage = chatResponse.getResult().getOutput().getText();
        return responseMessage;
    }

}
