package org.openweathermap;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openweathermap.utils.ConfigFileReader;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        tags="@getService",
        plugin = "json:target/cucumber.json")

public class RunCukesTest {
    @BeforeClass
    public static void setBaseURI(){
        ConfigFileReader configFileReader=new ConfigFileReader("API-Env.properties");
        RestAssured.baseURI =configFileReader.getApiEnvironment();
    }
}
