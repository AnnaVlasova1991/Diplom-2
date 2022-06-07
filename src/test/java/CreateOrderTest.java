import client.StepsForDelete;
import client.StepsForGet;
import client.StepsForPost;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
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
    String accessToken = "";
    List<Data> ingredients;
    String idIngrOne;
    String idIngrTwo;
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
        idIngrOne = ingredients.get(0).get_id();
        idIngrTwo = ingredients.get(1).get_id();
        ingredientsForOrder.add(idIngrOne);
        ingredientsForOrder.add(idIngrTwo);

        StepsForPost.doPostRequestForCreateOrder(new CreateOrder(ingredientsForOrder), accessToken)
                .then().assertThat().body("success", equalTo(true)).and().statusCode(200);
    }

    @Test
    @DisplayName("Можно создать заказ без токена. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    @Description("Можно создать заказ без токена. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    public void createOrderWithoutTokenCorrectTest() {
        ingredients = StepsForGet.doGetRequestForGetIngridients().body().as(CreateOrder.class).getData();
        idIngrOne = ingredients.get(0).get_id();
        idIngrTwo = ingredients.get(1).get_id();
        ingredientsForOrder.add(idIngrOne);
        ingredientsForOrder.add(idIngrTwo);

        StepsForPost.doPostRequestForCreateOrderWithoutToken(new CreateOrder(ingredientsForOrder))
                .then().assertThat().body("success", equalTo(true)).and().statusCode(200);
    }

    @Test
    @DisplayName("Невозможно создать заказ без ингридиентов. Запрос возвращает правильный код ответа 400. Запрос возвращает success:false")
    @Description("Невозможно создать заказ без ингридиентов. Запрос возвращает правильный код ответа 400. Запрос возвращает success:false")
    public void createOrderWithoutIngredientUnorrectTest() {

        StepsForPost.doPostRequestForCreateOrderWithoutIngredient()
                .then().assertThat().body("success", equalTo(false)).and().statusCode(400);
    }

    @Test
    @DisplayName("Невозможно создать заказ без ингридиентов. Запрос возвращает правильный код ответа 400. Запрос возвращает success:false")
    @Description("Можно создать заказ без токена. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    public void createOrderUncorrectIngredientNegativeTest() {
        idIngrOne = Faker.instance().food().ingredient();
        idIngrTwo = Faker.instance().food().ingredient();
        ingredientsForOrder.add(idIngrOne);
        ingredientsForOrder.add(idIngrTwo);

        StepsForPost.doPostRequestForCreateOrderWithoutToken(new CreateOrder(ingredientsForOrder))
                .then().assertThat().statusCode(500);
    }

}
