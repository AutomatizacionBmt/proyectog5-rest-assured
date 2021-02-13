package com.company.cucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestCommonStepdefs {

    protected RequestSpecification request;
    protected Response response;

    @Given("El sistema esta listo para recibir peticiones")
    public void elSistemaEstaListoParaRecibirPeticiones() {

        request = given();

    }

    @Then("El codigo de estado de la respuesta del servicio debe ser {int}")
    public void elCodigoDeEstadoDeLaRespuestaDelServicioDebeSer(int statusCode) {

        response.
                then()
                .statusCode(statusCode);

    }



}
