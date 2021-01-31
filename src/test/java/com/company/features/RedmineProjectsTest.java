package com.company.features;

import com.company.config.RedmineConfig;
import com.company.entities.Entity;
import com.company.entities.Project;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RedmineProjectsTest extends RedmineConfig {


    @Test
    public void testProjectSerialization(){


        Project project = new Project();
        project.setName("RedmineProjectasdfgh1");
        project.setIdentifier("redmineprojectasdfgh001");
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


        /*Prueba en Rest Assured
        {
            "name": "RedmineProjectasdfgh1",
            "identifier": "redmineprojectasdfgh001",
            "description": "Esta es una descripción...",
            "inherit_members": false,
            "is_public": true
         }
         */

        /*Prueba en Postman
        {
            "project": {
                "name": "Redmine Project Postman",
                "identifier": "yedminepuwafgo8",
                "description": "Redmine Project for api testing",
                "inherit_members": false,
                "is_public": true
                }

        }
         */

        /*
        {
            "project": {
                "name": "RedmineProjectasdfgh1",
                "identifier": "redmineprojectasdfgh001",
                "description": "Esta es una descripción...",
                "inherit_members": false,
                "is_public": true
            }
        }
         */


    }




}
