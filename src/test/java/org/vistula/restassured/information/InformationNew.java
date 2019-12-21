package org.vistula.restassured.information;


import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.vistula.restassured.RestAssuredTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;

public class InformationNew extends RestAssuredTest {
    @Test
    public void shouldCreateNewPlayer() {

        JSONObject requestParams = new JSONObject();
        String myName = RandomStringUtils.randomAlphabetic(10);

        requestParams.put("nationality", "Poland");
        requestParams.put("name", myName);
        requestParams.put("salary", 500);
        requestParams.put("id", 3);
        String cos=given().header("Content-Type", "application/json")
                .body(requestParams.toString())
                .post("/information")
                .then()
                .log().all()
                .statusCode(201)
                .extract().path("name");
/*.body("nationality", equalTo("Poland"))
.body("salary", equalTo(500))
.body("name", equalTo(myName))
.body("id", greaterThan(0));*/
        System.out.println(cos);
    }

    @Test
    public void shouldPatchNewPlayer() {

        JSONObject requestParams = new JSONObject();
        String myName = RandomStringUtils.randomAlphabetic(10);
        given().header("Content-Type", "application/json;charset=utf-8").when()
                .body("{\"nationality\":\"Romania\"}")
                .patch("/information/3")
                .then()
                .log().all()
                .statusCode(200)
                .body("nationality", equalTo("Romania"));
    }

    @Test
    public void shouldPatch2NewPlayer() {

        JSONObject requestParams = new JSONObject();
        String myName = RandomStringUtils.randomAlphabetic(10);
        given().header("Content-Type", "application/json;charset=utf-8").when()
                .body("{\"name\":\"Messi\"}")
                .patch("/information/3")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("Messi"));
    }


    @Test
    public void deletePlayer(){
        given().delete("/information/3")
                .then()
                .log().all()
                .statusCode(204);
    }
}
