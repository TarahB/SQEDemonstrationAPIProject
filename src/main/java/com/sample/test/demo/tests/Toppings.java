package com.sample.test.demo.tests;

import com.sample.test.demo.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Toppings extends BaseTest {


// This Test Will Pass Expected: Status Code 200 Actual: 200
    @Test(description = "Get all toppings")
    public void getAllToppings() {
        Response response = given().
                accept(ContentType.JSON).
                basePath("toppings").
                when().
                get().prettyPeek();

        response.then().assertThat().statusCode(200).log().ifError();
    }

    // This Test Will Fail Expected: Status Code 204 Actual: 201
    @Test(description = "Add new topping")
    public void addTopping() {
        String body = "{\"name\": \"Sausage\"}";

        Response response = given().
                contentType(ContentType.JSON).
                basePath("toppings").
                when().
                body(body).
                post().prettyPeek();

        response.then().assertThat().statusCode(204).log().ifError();
    }

    // This Test Will Fail Expected: Status Code 405 Actual: 201
    @Test(description = "Duplicate new topping", dataProvider = "toppings")
    public void duplicateTopping(String id, String name) {
        String body = String.format("{\"name\": \"%s\"}", name);

        Response response = given().
                contentType(ContentType.JSON).
                basePath("toppings").
                when().
                body(body).
                post().prettyPeek();

        response.then().assertThat().statusCode(405).log().ifError();
    }

    // This Test Will Pass Expected: Status Code 200 Actual: 200
    @Test(dataProvider = "toppings", description = "Check all toppings")
    public void verifyToppings(String id, String name) {
        Response response = given().
                contentType(ContentType.JSON).
                basePath("toppings/{id}").
                pathParam("id", id).
                when().
                get().prettyPeek();

        response.then().assertThat().statusCode(200).body("name", is(name)).log().ifError();
    }

    // This Test Will Pass Expected: Status Code 200 Actual: 200
    @Test(dataProvider = "toppings", description = "Delete toppings")
    public void deleteToppings(String id, String name) {
        Response response = given().
                contentType(ContentType.JSON).
                basePath("toppings/{id}").
                pathParam("id", id).
                when().
                delete().prettyPeek();
        response.then().assertThat().statusCode(200).log().ifError();
    }

    @DataProvider
    public static Object[][] toppings() {
        return new Object[][]{
                {"1", "Diced Mango"},
                {"2", "Olives"},
                {"3", "Mushrooms"},
                {"4", "Caramelized onions"},
                {"5", "Italian Ham"},
                {"6", "Classic Pepperoni"},
                {"7", "Salami"},
                {"8", "Provolone cheese"},
                {"9", "Extra cheese"},
                {"10", "Mozzarella cheese"},
                {"11", "Parmesan cheese"},
        };
    }
}
