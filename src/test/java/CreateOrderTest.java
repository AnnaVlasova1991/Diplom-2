import client.StepsForDelete;
import client.StepsForGet;
import client.StepsForPost;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import model.CreateOrder;
import model.Data;
import model.UserCreate;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest {
    String email = Faker.instance().internet().emailAddress();
    String password = Faker.instance().internet().password();
    String name = Faker.instance().name().firstName();
    String accessToken;
    List<Data> ingredients;
    String idIngr1;
    String idIngr2;
    List<String> ingredientsForOrder = new ArrayList<>();

    @After
    public void deleteData() {
        StepsForDelete.doDeleteRequestForDeleteUser(accessToken);
    }

    @Test
    @DisplayName("Можно создать заказ. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    @Description("Можно создать заказ. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    public void createOrderCorrectTest() {
        accessToken = StepsForPost.doPostRequestForCreateUser(new UserCreate(email, password, name)).body().as(UserCreate.class).getAccessToken();
        ingredients = StepsForGet.doGetRequestForGetIngridients().body().as(CreateOrder.class).getData();
        idIngr1 = ingredients.get(0).get_id();
        idIngr2 = ingredients.get(1).get_id();
        ingredientsForOrder.add(idIngr1);
        ingredientsForOrder.add(idIngr2);

        StepsForPost.doPostRequestForCreateOrder(new CreateOrder(ingredientsForOrder), accessToken)
                .then().assertThat().body("success", equalTo(true)).and().statusCode(200);
    }

    @Test
    @DisplayName("Можно создать заказ без токена. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    @Description("Можно создать заказ без токена. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    public void createOrderWithoutTokenCorrectTest() {
        ingredients = StepsForGet.doGetRequestForGetIngridients().body().as(CreateOrder.class).getData();
        idIngr1 = ingredients.get(0).get_id();
        idIngr2 = ingredients.get(1).get_id();
        ingredientsForOrder.add(idIngr1);
        ingredientsForOrder.add(idIngr2);

        accessToken = "";

        StepsForPost.doPostRequestForCreateOrderWithoutToken(new CreateOrder(ingredientsForOrder))
                .then().assertThat().body("success", equalTo(true)).and().statusCode(200);
    }

    @Test
    @DisplayName("Невозможно создать заказ без ингридиентов. Запрос возвращает правильный код ответа 400. Запрос возвращает success:false")
    @Description("Невозможно создать заказ без ингридиентов. Запрос возвращает правильный код ответа 400. Запрос возвращает success:false")
    public void createOrderWithoutIngredientUnorrectTest() {
        accessToken = "";

        StepsForPost.doPostRequestForCreateOrderWithoutIngredient()
                .then().assertThat().body("success", equalTo(false)).and().statusCode(400);
    }

    @Test
    @DisplayName("Невозможно создать заказ без ингридиентов. Запрос возвращает правильный код ответа 400. Запрос возвращает success:false")
    @Description("Можно создать заказ без токена. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    public void createOrderUncorrectIngredientNegativeTest() {
        idIngr1 = Faker.instance().food().ingredient();
        idIngr2 = Faker.instance().food().ingredient();
        ingredientsForOrder.add(idIngr1);
        ingredientsForOrder.add(idIngr2);

        accessToken = "";

        StepsForPost.doPostRequestForCreateOrderWithoutToken(new CreateOrder(ingredientsForOrder))
                .then().assertThat().statusCode(500);
    }

}
