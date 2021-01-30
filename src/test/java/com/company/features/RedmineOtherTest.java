package com.company.features;

import com.company.config.RedmineConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RedmineOtherTest extends RedmineConfig {


    @Test
    public void testValidateResponseDataProject(){

        given()
                .pathParam("id", 1).
        when()
                .get("projects/{id}.json").
        then()
                .statusCode(200)
                .body("project.id",equalTo(1))
                .body("project.name", equalTo("ProyectoSoftwareTesing"))
                .body("project.identifier",equalTo("proyectosoftwaretesing"))
                .body("project.homepage", equalTo("HomePage"));
    }


}
