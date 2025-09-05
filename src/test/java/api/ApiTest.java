package api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.BaseApiTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ApiTest extends BaseApiTest {

    private static String jwtToken;


    @Test
    public void jwtTokenTest() {
        Response response = UserApi.getJwtToken("SWSTestUser", "K31KRm6vvHYHIFCBZQBoTJARzsaubpZervGV9XeqiSQZ0Fcc9K4zkRhwjDgeo9AU");

        Assertions.assertEquals(200, response.getStatusCode(), "Response code");
        Assertions.assertNotNull(response.asString(), "Response body not empty.");

        jwtToken = response.asString();

    }

    @Test
    public void getClientsTest() {
        Response response = UserApi.getClients(jwtToken);

        Assertions.assertEquals(200, response.getStatusCode(), "Response code");
        Assertions.assertNotNull(response.asString(), "Response body not empty.");


        List<ClientDto> clientInformations = response.as(new TypeRef<List<ClientDto>>() {
        });

        int numberOfPruducts = clientInformations.get(0).products.size();

        System.out.println(String.format("Client has %s products!", numberOfPruducts));
    }

}
