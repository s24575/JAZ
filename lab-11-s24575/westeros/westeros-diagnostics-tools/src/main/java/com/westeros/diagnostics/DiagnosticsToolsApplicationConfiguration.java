package com.westeros.diagnostics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DiagnosticsToolsApplicationConfiguration {
    @Bean
    public RestTemplate restTemplate() { return new RestTemplate(); }
}
