package com.company.cucumber.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class RestIssuesStepdefs {

    private RequestSpecification request;
    private Response response;


    @Given("El sistema esta listo para recibir peticiones")
    public void elSistemaEstaListoParaRecibirPeticiones() {

        request = given();

    }

    @When("Un usuario envia una peticion al servicio de listar todos los issues")
    public void unUsuarioEnviaUnaPeticionAlServicioDeListarTodosLosIssues() {

        response = request.when()
                .get("issues.json");

    }


    @Then("El codigo de estado de la respuesta del servicio debe ser {int}")
    public void elCodigoDeEstadoDeLaRespuestaDelServicioDebeSer(int statusCode) {

        response.
                then()
                .statusCode(statusCode);

    }

    @When("Un usuario envia una peticion al servicio de obtener un issue por su id")
    public void unUsuarioEnviaUnaPeticionAlServicioDeObtenerUnIssuePorSuId(Map<String, String> issue) {

    response = request.
            pathParam("idIssue", issue.get("id")).
    when()
            .get("issues/{idIssue}.json");


    }

    @And("El sistema debe responder con la siguiente data:")
    public void elSistemaDebeResponderConLaSiguienteData(Map<String, String> expectedData) {

        JsonPath actualData = new JsonPath(response.getBody().asString());

        Assert.assertEquals("El id no es el correcto",
                Integer.parseInt(expectedData.get("id")),
                actualData.getInt("issue.id"));

        Assert.assertEquals("El subject no es correcto",
                expectedData.get("subject"),
                actualData.getString("issue.subject"));

        Assert.assertEquals("La descripcion no es correcta",
                expectedData.get("description"),
                actualData.getString("issue.description"));

        Assert.assertEquals("La fecha no es correcta",
                expectedData.get("start_date"),
                actualData.getString("issue.start_date"));

    }
}
