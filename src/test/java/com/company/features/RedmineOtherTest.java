package com.company.features;

import com.company.config.RedmineConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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

    @Test
    public void testValidateResponseDataSecondProject(){

        given().
        when()
                .get("projects.json").
        then()
                .statusCode(200)
                .body("projects[1].id", equalTo(1))
                .body("projects[1].name",equalTo("ProyectoSoftwareTesing"))
                .body("projects[1].identifier", equalTo("proyectosoftwaretesing"))
                .body("projects[1].description", equalTo(""));

    }

    @Test
    public void testValidateResponseAnyProject(){

        given().
         when()
                .get("projects.json").
         then()
                .statusCode(200)
                .body("projects.id",hasItems(8,7));

    }

    @Test
    public void extractAllProjectsIds(){

        Response response =
                            given().
                            when()
                                    .get("projects.json").
                            then()
                                   .statusCode(200)
                                   .extract().response();


        List<Integer> idProjects = response.path("projects.id");

        for(Integer id : idProjects){

            System.out.println("Id Project: "+id);
        }

    }

    @Test
    public void getSingleProjectJSONAndValidateIfExistsFields(){

       /*
       {
            "project": {
                            "id": 1,
                            "name": "ProyectoSoftwareTesing",
                            "identifier": "proyectosoftwaretesing",
                            "description": "",
                            "homepage": "HomePage",
                            "status": 1,
                            "is_public": true,
                            "inherit_members": false,
                            "created_on": "2020-12-11T00:52:30Z",
                            "updated_on": "2020-12-11T00:52:30Z"
                        }
        }
        */


        given()
                .pathParam("idProject", 1).
        when()
                .get("projects/{idProject}.json").
        then()
                .statusCode(200)
                .body("$", hasKey("project"))
                .body("project",hasKey("id"))
                .body("project",hasKey("identifier"))
                .body("project",hasKey("homepage"))
                .body("project",hasKey("is_public"));

    }


}
