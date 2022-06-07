package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StepsForDelete extends BaseHttpClient {
    @Step(value = "Удаляем пользователя. Делаем POST запрос по endpoint: \"/api/auth/user\". Передаем токен")
    public static Response doDeleteRequestForDeleteUser(String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .post(BASEURL + "/api/auth/user");
    }
}
