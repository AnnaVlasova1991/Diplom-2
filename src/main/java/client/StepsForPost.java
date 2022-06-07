package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.CreateOrder;
import model.UserCreate;

import static io.restassured.RestAssured.given;

public class StepsForPost extends BaseHttpClient {

    @Step(value = "Создаем пользователя. Делаем POST запрос по endpoint: \"/api/auth/register\". Передаем email, password, name")
    public static Response doPostRequestForCreateUser(UserCreate userCreate) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(userCreate)
                .when()
                .post(BASEURL + "/api/auth/register");
    }

    @Step(value = "Авторизация пользователя. Делаем POST запрос по endpoint: \"/api/auth/login\". Передаем email, password")
    public static Response doPostRequestForLoginUser(UserCreate userCreate, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .and()
                .body(userCreate)
                .when()
                .post(BASEURL + "/api/auth/login");
    }

    @Step(value = "Создание заказа. Делаем POST запрос по endpoint: \"/api/orders\". Передаем id ингридиентов, токен клиента")
    public static Response doPostRequestForCreateOrder(CreateOrder createOrder, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .and()
                .body(createOrder)
                .when()
                .post(BASEURL + "/api/orders");
    }

    @Step(value = "Создание заказа. Делаем POST запрос по endpoint: \"/api/orders\". Передаем id ингридиентов")
    public static Response doPostRequestForCreateOrderWithoutToken(CreateOrder createOrder) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(createOrder)
                .when()
                .post(BASEURL + "/api/orders");
    }

    @Step(value = "Создание заказа. Делаем POST запрос по endpoint: \"/api/orders\". Не передаем id ингридиентов")
    public static Response doPostRequestForCreateOrderWithoutIngredient() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .post(BASEURL + "/api/orders");
    }
}
