package com.netlify.step_definitions;

import com.netlify.managers.Driver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Allure;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class Hook {

    @Before
    public void setup(){
        Driver.getDriver().manage().window().setSize(new Dimension(1440,900));
    }

    @After
    public void teardown(Scenario scenario){

        if(scenario.isFailed()){
            Allure.addAttachment("ScreenShot", new ByteArrayInputStream((((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES))));
        }
       Driver.close();
    }
}