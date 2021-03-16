package org.openweathermap.step_def;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.openweathermap.utils.ConfigFileReader;
import org.openweathermap.utils.RestServices;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;

public class GetCurrentWeatherDataSteps extends RestServices {
    public Response response;
    public RestServices services=new RestServices();
    ConfigFileReader configFileReader=new ConfigFileReader("API-Env.properties");

    @Given("^I have base uri$")
    public void i_have_base_uri() throws Throwable {
        assertThat(configFileReader.getApiEnvironment(), is(endsWith("org/data/2.5/weather")));
    }

    @When("^I execute get request with query param \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_execute_get_request_with_query_param_and(String key, String value) throws Throwable {
        response  = services.getService(key,value);
    }

    @When("^I execute get request by geographic coordinates with query params \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_execute_get_request_by_geographic_coordinates_with_query_params(String lon, String lat) throws Throwable {
          response  =services.getByCoordinatesService(lon,lat);
          response.prettyPrint();
    }

    @When("^I execute get request with wrong api key and query param as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_execute_get_request_with_wrong_api_key_and_query_param_as_and(String key, String value) throws Throwable {
          response = services.getServiceInvalidApiKey(key,value);
          response.prettyPrint();
    }

    @When("^I execute get request without api key and query param as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_execute_get_request_without_api_key_and_query_param_as_and(String key, String value) throws Throwable {
        response = services.getServiceNoApiKey(key,value);
        response.prettyPrint();
    }


    @Then("^I should see status code as \"([^\"]*)\"$")
    public void i_should_see_status_code_as(int expectedStatusCode) throws Throwable {
        response.then().statusCode(expectedStatusCode);

    }

    @Then("^I should see the message body with \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_should_see_the_message_body_with_as(String msgKey, String msgValue) throws Throwable {
        response.then().assertThat().body(msgKey, equalTo(msgValue));
    }

    @Then("^I see response with values of \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void i_see_response_with_values_of(String name, String id, String lon, String lat) throws Throwable {
        List<String> expectedList = Arrays.asList(name,id, lon, lat);
        List<String> actualList = new ArrayList<>();

        actualList.add(response.then().extract().path("name").toString());
        actualList.add(response.then().extract().path("id").toString());
        HashMap<String, Float> body=response.then().extract().path("coord");
        actualList.add(body.get("lon").toString());
        actualList.add(body.get("lat").toString());

       assertThat(actualList,equalTo(expectedList));
    }
}
