package com.company.cucumber.stepdefs;

import com.company.entities.Entity;
import com.company.entities.Project;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class RestProjectsStepdefs {

    protected RestCommonStepdefs commonSteps;

    public RestProjectsStepdefs(RestCommonStepdefs commonSteps){
        this.commonSteps = commonSteps;
    }


    @When("El usuario envia una peticion al servicio de crear un proyecto")
    public void elUsuarioEnviaUnaPeticionAlServicioDeCrearUnProyecto(Map<String, String > projectData) {


        Integer randomNumber = (new Random()).nextInt(900000) + 100000;

        Project project = new Project();
        project.setName(projectData.get("name") + randomNumber);
        project.setIdentifier(projectData.get("identifier") + randomNumber);
        project.setDescription(projectData.get("description") + randomNumber);
        project.setInherit_members(Boolean.parseBoolean(projectData.get("inherit_members")));
        project.setIs_public(Boolean.parseBoolean(projectData.get("is_public")));

        Entity entity = new Entity(project);

        commonSteps.response = commonSteps.request.
                body(entity)
                .when()
                    .post("projects.json");

    }

    @When("El usuario crea varios proyectos:")
    public void elUsuarioCreaVariosProyectos(DataTable dataTable) {


        Integer randomNumber = (new Random()).nextInt(900000) + 100000;

        Project project = new Project();
        project.setName(dataTable.row(1).get(0) + randomNumber);
        project.setIdentifier(dataTable.row(1).get(1) + randomNumber);
        project.setDescription(dataTable.row(1).get(2) + randomNumber);
        project.setInherit_members(Boolean.parseBoolean(dataTable.row(1).get(3)));
        project.setIs_public(Boolean.parseBoolean(dataTable.row(1).get(4)));

        Entity entity = new Entity(project);

        commonSteps.response = commonSteps.request.
                body(entity)
                .when()
                .post("projects.json");

    }

    @When("Un usuario envia una peticion al servicio de obtener un proyecto por su id")
    public void unUsuarioEnviaUnaPeticionAlServicioDeObtenerUnProyectoPorSuId(Map<String, String > project) {

        commonSteps.response = commonSteps.request.
                pathParam("idProject", project.get("id")).
                when()
                        .get("projects/{idProject}.json");

    }

    @And("El sistema debe responder con la siguiente data del proyecto:")
    public void elSistemaDebeResponderConLaSiguienteDataDelProyecto(Map<String, String > expectedData) {

        JsonPath actualData = new JsonPath(commonSteps.response.getBody().asString());

        Assert.assertEquals("El id del proyecto no es el correcto",
                Integer.parseInt(expectedData.get("id")),
                actualData.getInt("project.id"));

        Assert.assertEquals("El nombre del proyecto no es el correcto",
                expectedData.get("name"),
                actualData.getString("project.name"));

    }
}
