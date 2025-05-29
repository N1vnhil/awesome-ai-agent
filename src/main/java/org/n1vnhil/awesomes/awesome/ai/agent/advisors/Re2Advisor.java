package org.n1vnhil.awesomes.awesome.ai.agent.advisors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.api.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Re2Advisor implements CallAroundAdvisor, StreamAroundAdvisor {

    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        return chain.nextAroundCall(before(advisedRequest));
    }

    @Override
    public Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) {
        return chain.nextAroundStream(before(advisedRequest));
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private AdvisedRequest before(AdvisedRequest request) {
        Map<String, Object> requestParams = new HashMap<>(request.userParams());
        requestParams.put("re2_input_query", request.userText());
        return AdvisedRequest.from(request)
                .userText("""
                        {re2_input_query}
                        再次阅读用户的题目要求：{re2_input_query}
                        """)
                .userParams(requestParams)
                .build();
    }

}
