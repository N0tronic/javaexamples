package com.example.rules.rulebook.rulebase;

import com.deliveredtechnologies.rulebook.annotation.*;
import lombok.extern.log4j.Log4j2;

@Rule(order = 1)
@Log4j2
public class TestRule {

    @Given("hello")
    private String hello;
    @Given("world")
    private String world;

    @Result
    private String result;

    @When
    public boolean when() {
        return matchFacts(hello, world);
    }

    @Then
    public void then() {
        log.info(this.getClass().getSimpleName() + " hat funktioniert.");
        result = "True";
    }

    private boolean matchFacts(String hello, String world) {
        return hello.equals("Hello ") && world.equals("World");
    }

}
