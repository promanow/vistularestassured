package org.vistula.restassured.information;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.vistula.restassured.RestAssuredTest;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;

public class InformationControllerTest extends RestAssuredTest {

    @Test
    public void shouldGetAll() {
        given().get("/information")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", is(2));
    }

    @Test
    public void shouldCreateNewPlayer() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("nationality", "Poland");
        String myName = RandomStringUtils.randomAlphabetic(10);
        requestParams.put("name", myName);
        requestParams.put("salary", 40000);


        given().header("Content-Type", "application/json")
                .body(requestParams.toString())
                .post("/information")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", equalTo(myName))
                .body("nationality", equalTo("Poland"))
                .body("salary", equalTo(40000))
                .body("id", greaterThan(0));

    }

    @Test
            public void deletePlayer(){
        given().delete("/information/4")
                .then()
                .log().all()
                .statusCode(204);
    }
}
