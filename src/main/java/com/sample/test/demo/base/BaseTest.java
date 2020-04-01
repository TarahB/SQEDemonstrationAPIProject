package com.sample.test.demo.base;

import com.sample.test.demo.utils.ConfigurationReader;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;


public class BaseTest {
/*
Created Base Test and I put @BeforeClass annotation to define baseURI
BaseTest is to be extended by Test Classes
 */
    @BeforeClass
    public void setup(){
        baseURI = ConfigurationReader.getProperty("base.uri");
    }

}
