package org.n1vnhil.awesomes.awesome.ai.agent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Map;

public class AliyunHttpApiInvoke {

    public static void main(String[] args) {
        String url = "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions";
        Map<String, String> headers = Map.of(
                "Authorization", "Bearer " + System.getenv("BAILIAN_API_KEY"),
                "Content-Type", "application/json"
        );

        JSONObject requestBody = new JSONObject();
        requestBody.set("model", "qwen-plus");

        JSONObject[] message = new JSONObject[2];
        message[0] = new JSONObject();
        message[0].set("role", "system");
        message[0].set("content", "你是一名Java开发工程师");

        message[1] = new JSONObject();
        message[1].set("role", "user");
        message[1].set("content", "接口和抽象类的区别是什么");

        requestBody.set("messages", message);

        HttpRequest request = HttpRequest.post(url).addHeaders(headers).body(requestBody.toString());
        System.out.println(request.toString());
        HttpResponse response = request.execute();

        if(response.isOk()) {
            System.out.println(response.body());
        } else {
            System.out.println(response.getStatus());
            System.out.println(response.body());
        }
    }

}
