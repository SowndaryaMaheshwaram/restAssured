package org.example;

import io.restassured.response.Response;
import org.junit.Test;


import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ExampleTest {
    String BASE_URL = "https://x8ki-letl-twmt.n7.xano.io/api:gHPd8le5";
    String TRANSACTION = "/transaction";

    public static int ID_FROM_POST_TRANSACTION;

    @Test
    public void testPostMethod() {

       File payload = new File("src/main/resources/transaction_testdata.json");

       Response response = given().urlEncodingEnabled(false).
                contentType("application/json").
                accept("application/json").
                body(payload).
        when().
                post(BASE_URL+TRANSACTION).
        then().
                statusCode(200).
                body("id", notNullValue()).extract().response();

       if(response.body().path("id")!=null){
           ID_FROM_POST_TRANSACTION = response.body().path("id");
       }

    }
    @Test
    public void testGetMethod() {
        given().
                urlEncodingEnabled(false).
                accept("application/json").
        when().
                get(BASE_URL+TRANSACTION+"/{transaction_id}",ID_FROM_POST_TRANSACTION).
        then().
                statusCode(200).
                body("sentCoin",equalTo("INR")).
                body("receivedCoin",equalTo("USDT")).
                body("sentCoinAmount",equalTo(20)).
                body("receivedCoinAmount",equalTo(2));
    }
}
