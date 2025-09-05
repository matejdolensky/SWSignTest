package api;

import core.BaseApiTest;
import io.qameta.allure.Allure;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.ExcelReader;

import java.util.List;
import java.util.stream.Stream;

public class JwtAndClientDetailTest extends BaseApiTest {


    @ParameterizedTest(name = "{index} => {1}")
    @MethodSource("dataProvider")
    public void testJwtAndClientDetail(TestEntry entry, String displayName) {
        Response response;

        // -----------------------------
        // Step 1: Get JWT
        // -----------------------------
        Allure.step("Getting jwt token for user: " + entry.getUsername());

        response = UserApi.getJwtToken(entry.getUsername(), entry.getUserSecret());

        Allure.addAttachment("Generate jwt token response", response.asString());

        Assertions.assertEquals(entry.getExpectedJwtResponseCode(), response.getStatusCode(), "Response code");
        Assertions.assertNotNull(response.asString(), "Response body not empty.");

        String jwtToken = response.asString();


        // -----------------------------
        // Step 2: Get client detail with JWT
        // -----------------------------
        Allure.step("Call protected endpoint with JWT");

        response = UserApi.getClients(jwtToken);

        Assertions.assertEquals(entry.getExpectedClientResponseCode(), response.getStatusCode(), "Response code");
        Assertions.assertNotNull(response.asString(), "Response body not empty.");

        if (entry.getExpectedClientResponseCode() <= 201) {
            List<ClientDto> clientInformation = response.as(new TypeRef<List<ClientDto>>() {
            });

            int numberOfProducts = clientInformation.get(0).products.size();


            System.out.printf("Client has %s products!%n", numberOfProducts);
        }


    }

    private static Stream<Arguments> dataProvider() {
        return ExcelReader.readTestEntries("Testdata.xlsx").stream().map(e -> Arguments.of(e, e.getTestName()));
    }
}
