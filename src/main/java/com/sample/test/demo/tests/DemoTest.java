package com.sample.test.demo.tests;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.sample.test.demo.base.BaseTest;

public class DemoTest extends BaseTest {


    @Test
    public void demoTest() {
        when().
                get("orders").prettyPeek().
                then().
                and().assertThat().statusCode(200);

    }


}
