package com.sample.test.demo.tests;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.sample.test.demo.utils.BaseTest;

public class DemoTest extends BaseTest {

    private static final String url = "https://my-json-server.typicode.com/sa2225/demo/orders";

    @Test
    public void demoTest() {
        given().when().get(url).then().and().assertThat().statusCode(200);

        Response response=  given().when().get(url).prettyPeek();
    }


}
