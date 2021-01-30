package com.company.features;


import com.company.config.RedmineConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RedmineTest extends RedmineConfig {


    @Test
    public void listarTodosLosIssuesJSON(){

     given().
             //.baseUri("http://198.211.98.120:8081").
            //cuando tengamos alguna version de nuestra api
            //.basePath("/v2").
            //basePath("/").
     when()
             .get("issues.json").
     then()
             //.log().all()
             .statusCode(200);
    }

    @Test
    public void crearUnNuevoIssueJSON(){

        String issueBody = "{\n" +
                "  \"issue\": {\n" +
                "    \"project_id\": 1,\n" +
                "    \"subject\": \"Issue creado por jhurtado desde Restassured\",\n" +
                "    \"priority_id\": 4\n" +
                "  }\n" +
                "}";

        given().
        when()
                .body(issueBody)
                .post("issues.json").
        then()
                .statusCode(201);
    }

    @Test
    public void actualizarUnIssueJSON(){

        String issueBody = "{\n" +
                "  \"issue\": {\n" +
                "    \"subject\": \"Issue modificado por jhurtado\",\n" +
                "    \"description\": \"Esta es una descripcion\",\n" +
                "    \"priority_id\": 5,\n" +
                "    \"notes\": \"The subject was changed\" \n" +
                "  }\n" +
                "}";

        given()
                .pathParam("idIssue", 72).
        when()
                .body(issueBody)
                .put("issues/{idIssue}.json").
        then()
                .statusCode(204);
    }

    @Test
    public void eliminarUnIssueJSON(){

        given().
        when()
                .delete("issues/68.json").
        then()
                .statusCode(204);
    }

    @Test
    public void obtenerUnIssueJSON(){
        given()
                .pathParam("idIssue",72).
        when()
                .get("issues/{idIssue}.json").
        then()
                .statusCode(200);
    }

}
