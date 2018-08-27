package com.example.rules.rulebook;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class Application {

    private final RuleBook ruleBook;

    @Autowired
    public Application(RuleBook ruleBook) {
        this.ruleBook = ruleBook;
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    public void test() {
        NameValueReferableMap<String> facts = new FactMap<>();
        facts.setValue("hello", "Hello ");
        facts.setValue("world", "World");
        ruleBook.run(facts);
        log.info("Hier ist die Ausgabe: {}", ruleBook.getResult().toString());
    }

}
