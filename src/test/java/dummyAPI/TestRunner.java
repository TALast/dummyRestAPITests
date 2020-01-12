package dummyAPI;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        glue = { "dummyAPI.stepdefinitions"},
        tags = { "not @ignore" },
        plugin = { "pretty", "html:target/reports/cucumber/html", "json:target/report.json", "junit:target/junit.xml"}
)

public class TestRunner {
}