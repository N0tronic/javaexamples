package com.example.rules.rulebook.rulebase;

import com.deliveredtechnologies.rulebook.annotation.*;
import lombok.extern.log4j.Log4j2;

@Rule(order = 2)
@Log4j2
public class AnotherRule {

    @Given("hello")
    private String hello;
    @Given("world")
    private String world;

    @Result
    private String result;

    @When
    public boolean when() {
        return world == null;
    }

    @Then
    public void then() {
        log.info(this.getClass().getSimpleName() + " hat funktioniert.");
    }
}
