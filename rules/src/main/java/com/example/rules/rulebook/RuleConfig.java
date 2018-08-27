package com.example.rules.rulebook;

import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleConfig {

    @Bean
    public RuleBook ruleBook() {
        return new RuleBookRunner("com.example.rules.rulebook.rulebase");
    }
}
