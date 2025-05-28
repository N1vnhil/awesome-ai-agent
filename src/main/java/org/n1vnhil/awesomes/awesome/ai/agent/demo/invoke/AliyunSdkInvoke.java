package org.n1vnhil.awesomes.awesome.ai.agent.demo.invoke;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;

import java.util.Arrays;


public class AliyunSdkInvoke {

    private static GenerationResult invoke() throws NoApiKeyException, InputRequiredException {
        Generation generation = new Generation();
        Message sysMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("你是一名Java开发工程师")
                .build();

        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content("请问接口和抽象类的区别是什么？")
                .build();

        GenerationParam param = GenerationParam.builder()
                .apiKey("")
                .model("qwen-turbo")
                .messages(Arrays.asList(sysMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        return generation.call(param);
    }

    public static void main(String[] args) {
        try {
            GenerationResult result = invoke();
            System.out.println(JsonUtils.toJson(result));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
