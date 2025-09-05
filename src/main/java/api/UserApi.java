package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {

    public static Response getJwtToken(String userName, String userSecret) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("userSecret", userSecret)
                .when()
                .post("/jwt/{userName}", userName);

    }

    public static Response getClients(String token) {
        return given()
                .auth().oauth2(token)
                .post("clients");

    }
}
