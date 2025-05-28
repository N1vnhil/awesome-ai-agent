package org.n1vnhil.awesomes.awesome.ai.agent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "bailian")
public class AliyunBailianProperties {

    private String apikey;

}
