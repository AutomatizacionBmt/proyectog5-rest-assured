package com.company.features;

import com.company.config.RedmineConfig;
import com.company.entities.Entity;
import com.company.entities.Project;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RedmineProjectsTest extends RedmineConfig {


    @Test
    public void testProjectSerialization(){


        Project project = new Project();
        project.setName("RedmineProjectasdfgh2");
        project.setIdentifier("redmineprojectasdfgh2");
        project.setDescription("Esta es una descripción...");
        project.setInherit_members(false);
        project.setIs_public(true);

        Entity entity = new Entity(project);

        given()
                .body(entity).
        when()
                .post("projects.json").
        then()
                .statusCode(201);

    }

    @Test
    public void testProjectDeserialization(){

        Response response =
                        given()
                                .pathParam("idProject", 13).
                        when()
                                .get("projects/{idProject}.json");
                        //then()
                        //        .statusCode(200);


        response.then().statusCode(200);


        Entity  entity = response.getBody().as(Entity.class);
        Project project = entity.getProject();

        System.out.println(project.toString());

    }

    @Test
    public void testProjectValidateSchemaJSON(){

        given()
                .pathParam("idProject", 13).
        when()
                .get("projects/{idProject}.json").
        then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/project_schema.json"));

    }




}
