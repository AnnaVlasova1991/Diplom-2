package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StepsForGet extends BaseHttpClient {
    @Step(value = "Получаем данные об ингридиентах. Делаем GET запрос по endpoint: \"/api/ingredients\".")
    public static Response doGetRequestForGetIngridients() {
        return given()
                .get(BASEURL + "/api/ingredients");
    }
    @Step(value = "Получаем данные о заказах пользователя. Делаем GET запрос по endpoint: \"/api/orders\" c токеном.")
    public static Response doGetRequestForGetUserOrder(String token) {
        return given()
                .header("Authorization", token)
                .get(BASEURL + "/api/orders");
    }
    @Step(value = "Получаем данные о заказах пользователя. Делаем GET запрос по endpoint: \"/api/orders\" без токена.")
    public static Response doGetRequestForGetUserOrderWithoutToken() {
        return given()
                .get(BASEURL + "/api/orders");
    }
}
