package org.openweathermap.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestServices {

    ConfigFileReader configFileReader=new ConfigFileReader("APIKEY.properties");
    String apiKey =configFileReader.getApiKey();
    String invalidApiKey=configFileReader.getInvalidApiKey();
    String emptyApiKey=configFileReader.getInvalidApiKey();

    public Response getService(String key,String value){
       return given().contentType(ContentType.JSON)
               .queryParam(key,value)
               .queryParam("APPID",apiKey)
               .when()
               .get("/");
    }

    public Response getByCoordinatesService(String latValue,String lonValue){
       return given().contentType(ContentType.JSON)
                .queryParam("lat",latValue)
                .queryParam("lon",lonValue)
                .queryParam("APPID",apiKey)
                .when()
                .get("/");

    }
    public Response getServiceInvalidApiKey(String key,String value){
        return given().contentType(ContentType.JSON)
                .queryParam(key,value)
                .queryParam("APPID",invalidApiKey)
                .when()
                .get("/");
    }

    public Response getServiceNoApiKey(String key,String value){
        return given().contentType(ContentType.JSON)
                .queryParam(key,value)
                .queryParam("APPID",emptyApiKey)
                .when()
                .get("/");
    }
}
