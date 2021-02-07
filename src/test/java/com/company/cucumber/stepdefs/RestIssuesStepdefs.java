package com.company.cucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
}
