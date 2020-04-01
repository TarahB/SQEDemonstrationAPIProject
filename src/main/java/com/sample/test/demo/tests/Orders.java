package com.sample.test.demo.tests;

import com.sample.test.demo.base.BaseTest;
import com.sample.test.demo.beans.Order;
import com.sample.test.demo.beans.Pizza;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import java.util.List;
import static io.restassured.RestAssured.*;

public class Orders extends BaseTest {
    private static Logger logger = Logger.getLogger(Orders.class);

    // This Test Will Pass Expected: Status Code 200 Actual: 200
    @Test(description = "Get orders")
    public void get200() {
        given().
                basePath("orders").
                when().
                get().
                then().assertThat().statusCode(200).log().ifError();
    }

    // This Test Will Pass Expected: Status Code 404 Actual: 404
    @Test(description = "Wrong path for retrieving orders")
    public void badPath() {
        given().
                basePath("order").
                when().
                get().
                then().assertThat().statusCode(404).log().ifError();
    }

    //This Test Will Pass Expected: Status Code 200 Actual: 200
    @Test(description = "Get all orders")
    public void getAllOrders() {
        Response response = given().
                contentType(ContentType.JSON).
                basePath("orders/{id}").
                pathParam("id", 1).
                when().
                get().prettyPeek();

        response.then().assertThat().statusCode(200).log().ifError();

        Order order = response.jsonPath().getObject("", Order.class);
        Assert.assertNotNull(order);
        logger.info(order);
    }

    //This Test Will Fail Expected: Status Code 406 Actual: Status Code 201
    @Test(description = "Give incorrect number of toppings")
    public void incorrectNumberOfToppings() {
        Order order = new Order();
        Pizza pizza = new Pizza();
        pizza.setPizza("Medium 8 Slices - 2 toppings");
        pizza.setToppings(List.of());
        order.setItems(List.of(pizza));

        Response response = given().
                contentType(ContentType.JSON).
                basePath("orders").
                when().
                post().prettyPeek();

        response.then().assertThat().statusCode(406).log().ifError();
    }

// This Test Will Fail Expected: Status Code 408 Actual: 201
    @Test(description = "Pizza not specified")
    public void pizzaNotSpecified() {
        Order order = new Order();
        Pizza pizza = new Pizza();
        pizza.setToppings(List.of());
        order.setItems(List.of(pizza));

        Response response = given().
                contentType(ContentType.JSON).
                basePath("orders").
                when().
                post().prettyPeek();

        response.then().assertThat().statusCode(408).log().ifError();
    }

    // This Test Will Fail Expected: Status Code 407 Actual: 201
    @Test(description = "Give same toppings")
    public void sameToppings() {
        Order order = new Order();
        Pizza pizza = new Pizza();
        pizza.setPizza("Large 8 Slices - 4 toppings");
        pizza.setToppings(List.of("Salami","Salami","Salami","Salami"));
        order.setItems(List.of(pizza));

        Response response = given().
                contentType(ContentType.JSON).
                basePath("orders").
                when().
                post().prettyPeek();

        response.then().assertThat().statusCode(407).log().ifError();
    }


}
