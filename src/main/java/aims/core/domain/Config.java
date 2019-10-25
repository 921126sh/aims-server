package aims.core.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="koob")
public class Config {

    private String client;

    public String getClient() {
        return this.client;
    }
}

