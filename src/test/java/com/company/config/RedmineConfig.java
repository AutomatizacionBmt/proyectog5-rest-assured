package com.company.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;


public class RedmineConfig {

    public static RequestSpecification requestSpecification;

    public static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void setUp(){

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://198.211.98.120")
                .setPort(8081)
                .setBasePath("/")
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Redmine-API-Key", "ccb0c7377946014cdf7efe0346ed26a93cc7e1b7")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        responseSpecification = new ResponseSpecBuilder()
                //.expectResponseTime(lessThan(3000L))
                //.expectStatusCode(200)
                .build();


        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

    }

    @AfterClass
    public static void cleanUp(){
        RestAssured.reset();
    }
}
