package com.netlify.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"
        },
        glue = "com/netlify/step_definitions",
        dryRun = false,
        monochrome = true,
        plugin = {"pretty","html:target/default-cucumber-reports",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt" // failed runner.
        }
)
public class CucumberRunner {
}
