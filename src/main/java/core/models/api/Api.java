package core.models.api;

import io.restassured.RestAssured; //import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;

import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Api {
    //private String baseUrl;

    public Api(String baseUrl) {
        //this.baseUrl = baseUrl;
        RestAssured.baseURI  = baseUrl;
        RestAssured.basePath = "/";
        //RestAssured.port     = Integer.valueOf(80);
    }

    public Api open(String url) {
        //Selenide.open(baseUrl + url);
        return this;
    }

    public Api open() {
        given().
                queryParam("apiKey", "abc333").
                queryParam("code", 10).
                queryParam("type", 5).
                contentType("application/json").
        log().all().
        when().
                //get(baseUrl + "/").
                get("/v1").
        then().
                contentType("application/json").
                statusCode(200).
                extract().response();
        return this;
    }

    public Api openAnyUrl(String url) {
        //Selenide.open(url);
        return this;
    }
}
