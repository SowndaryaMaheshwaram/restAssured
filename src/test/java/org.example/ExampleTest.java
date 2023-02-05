package org.example;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ExampleTest {
    @Test
    public void testPostMethod() {

       String payload = "{\"coin1\":\"ABC\"," +
                "\"coin2\":\"XYZ\"," +
                "\"coin1Amount\":10," +
                "\"coin2Amount\":1}";

        given().urlEncodingEnabled(false).
                contentType("application/json").
                accept("application/json").
                body(payload).
        when().
                post("https://x8ki-letl-twmt.n7.xano.io/api:gHPd8le5/transaction").
        then().
                statusCode(200).
                body("id", notNullValue()).log().all();
    }

    @Test
    public void testGetMethod() {
        given().
                urlEncodingEnabled(false).
                accept("application/json").
                when().
                get(""+"/{transaction_id}",27).
                then().
                statusCode(200).log().all();
    }



}
