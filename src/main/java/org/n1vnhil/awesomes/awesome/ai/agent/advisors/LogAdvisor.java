package org.n1vnhil.awesomes.awesome.ai.agent.advisors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.api.*;
import org.springframework.ai.chat.model.MessageAggregator;
import reactor.core.publisher.Flux;

@Slf4j
public class LogAdvisor implements CallAroundAdvisor, StreamAroundAdvisor {
    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        AdvisedRequest request = this.before(advisedRequest);
        AdvisedResponse response = chain.nextAroundCall(request);
        this.observeAfter(response);
        return response;
    }

    @Override
    public Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) {
        AdvisedRequest request = this.before(advisedRequest);
        Flux<AdvisedResponse> response = chain.nextAroundStream(request);
        return new MessageAggregator().aggregateAdvisedResponse(response, this::observeAfter);
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private AdvisedRequest before(AdvisedRequest request) {
        String userPrompt = request.userText();
        log.info("User Request: {}", userPrompt);
        return request;
    }

    private void observeAfter(AdvisedResponse response) {
        log.info("AI Response: {}", response);
    }
}
