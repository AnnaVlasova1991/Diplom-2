package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.UserCreate;

import static io.restassured.RestAssured.given;

public class StepsForPatch extends BaseHttpClient {
    @Step(value = "Изменяем данные пользователя. Делаем PATCH запрос по endpoint: \"/api/auth/user\". Передаем email, password, токен")
    public static Response doPatchRequestForChangeUser(UserCreate userCreate, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .and()
                .body(userCreate)
                .when()
                .patch(BASEURL + "/api/auth/user");
    }

    @Step(value = "Изменяем данные пользователя. Делаем некорректный PATCH запрос по endpoint: \"/api/auth/user\". Передаем email, password")
    public static Response doPatchUncorrectRequestForChangeUser(UserCreate userCreate) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(userCreate)
                .when()
                .patch(BASEURL + "/api/auth/user");
    }
}
