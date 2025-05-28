package org.n1vnhil.awesomes.awesome.ai.agent.demo.invoke;

import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Map;

public class AliyunHttpApiInvoke {

    public static void main(String[] args) {
        String url = "https://dashscope.aliyuncs.com/compatible-mode/v1";
        Map<String, String> headers = Map.of(
                "Authorization", "",
                "Content-Type", "application/json"
        );
    }

}
