package com.sample.test.demo.tests;

import com.sample.test.demo.base.BaseTest;
import com.sample.test.demo.beans.Order;
import com.sample.test.demo.beans.Pizza;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class Pizzas extends BaseTest {

    //This Test Will Pass Expected: Status Code 201 Actual: 201
    @Test(description = "Create new pizza")
    public void createPizza() {
        Order order = new Order();
        Pizza pizza = new Pizza();
        pizza.setPizza("Medium 8 Slices - 2 toppings");
        pizza.setToppings(List.of("Mushrooms", "Cheese", "Tomatos", "Pineapple"));
        order.setItems(List.of(pizza));

        Response response = given().
                contentType(ContentType.JSON).
                basePath("orders").
                when().
                post().prettyPeek();

        response.then().assertThat().statusCode(201).assertThat().body("id", notNullValue()).log().ifError();
    }

    //This Test Will Pass Expected: Status Code 201 Actual: 201
    @Test(description = "Create new pizza no toppings")
    public void createPizzaNoToppings() {
        Order order = new Order();
        Pizza pizza = new Pizza();
        pizza.setPizza("Large 10 Slices - No Toppings");
        pizza.setToppings(List.of());
        order.setItems(List.of(pizza));

        Response response = given().
                contentType(ContentType.JSON).
                basePath("orders").
                when().
                post().prettyPeek();

        response.then().assertThat().statusCode(201).assertThat().body("id", notNullValue()).log().ifError();

    }

    // This Test Will Fail Expected: Status Code 407 Actual: 201
    @Test(description = "Create new pizza invalid pizza size with 2 toppings")
    public void createPizzaWrongSize() {
        Order order = new Order();
        Pizza pizza = new Pizza();
        pizza.setPizza("Medium 10 Slices - 2 Toppings");
        pizza.setToppings(List.of("Mushrooms", "Cheese"));
        order.setItems(List.of(pizza));

        Response response = given().
                contentType(ContentType.JSON).
                basePath("orders").
                when().
                post().prettyPeek();

        response.then().assertThat().statusCode(407).assertThat().body("id", notNullValue()).log().ifError();

    }

    //This Test Will Fail Expected: Status Code 408 Actual: 201
    @Test(description = "Create new pizza No Size")
    public void createPizzaNoSize() {
        Order order = new Order();
        Pizza pizza = new Pizza();
        pizza.setPizza("10 Slices - 2 Toppings");
        pizza.setToppings(List.of("Mushrooms", "Cheese"));
        order.setItems(List.of(pizza));

        Response response = given().
                contentType(ContentType.JSON).
                basePath("orders").
                when().
                post().prettyPeek();

        response.then().assertThat().statusCode(408).assertThat().body("id", notNullValue()).log().ifError();

    }

    //This Test Will Fail Expected: Status Code 408 Actual: 201
    @Test(description = "Create new pizza invalid pizza no size no toppings")
    public void createPizzaUnspecified() {
        Order order = new Order();
        Pizza pizza = new Pizza();
        pizza.setPizza("");
        pizza.setToppings(List.of(""));
        order.setItems(List.of(pizza));

        Response response = given().
                contentType(ContentType.JSON).
                basePath("orders").
                when().
                post().prettyPeek();

        response.then().assertThat().statusCode(408).assertThat().body("id", notNullValue()).log().ifError();
    }

}