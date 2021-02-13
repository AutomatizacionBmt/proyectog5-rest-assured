package com.company.cucumber.stepdefs;

import com.company.entities.Entity;
import com.company.entities.Project;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestProjectsStepdefs {

    private RequestSpecification request;
    private Response response;


    @When("El usuario envia una peticion al servicio de crear un proyecto")
    public void elUsuarioEnviaUnaPeticionAlServicioDeCrearUnProyecto(Map<String, String > projectData) {


        Project project = new Project();
        project.setName(projectData.get("name"));
        project.setIdentifier(projectData.get("identifier"));
        project.setDescription(projectData.get("description"));
        project.setInherit_members(Boolean.parseBoolean(projectData.get("inherit_members")));
        project.setIs_public(Boolean.parseBoolean(projectData.get("is_public")));

        Entity entity = new Entity(project);

        response = request.
                body(entity)
                .when()
                    .post("projects.json");

    }
}
