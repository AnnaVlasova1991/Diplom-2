import client.StepsForDelete;
import client.StepsForGet;
import client.StepsForPost;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import model.CreateOrder;
import model.UserCreate;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetOrderUserTest {
    String email = Faker.instance().internet().emailAddress();
    String password = Faker.instance().internet().password();
    String name = Faker.instance().name().firstName();
    String accessToken;

    @After
    public void deleteData() {
        StepsForDelete.doDeleteRequestForDeleteUser(accessToken);
    }

    @Test
    @DisplayName("Можно получить заказы пользователя. Запрос возвращает правильный код ответа 200. Успешный запрос возвращает success:true")
    @Description("Можно получить заказы пользователя. Запрос возвращает правильный код ответа 200. Успешный запрос возвращает success:true")
    public void getOrderUserCorrectTest() {
        accessToken = StepsForPost.doPostRequestForCreateUser(new UserCreate(email, password, name)).body().as(UserCreate.class).getAccessToken();
        StepsForGet.doGetRequestForGetUserOrder(accessToken)
                .then().assertThat().body("success", equalTo(true)).and().statusCode(200);
    }

    @Test
    @DisplayName("Невозможно получить заказы пользователя без авторизации. Запрос возвращает правильный код ответа 401. Запрос возвращает success:false")
    @Description("Невозможно получить заказы пользователя без авторизации. Запрос возвращает правильный код ответа 401. Запрос возвращает success:false")
    public void getOrderUserWithoutTokenNegativeTest() {
        accessToken = "";
        StepsForGet.doGetRequestForGetUserOrderWithoutToken()
                .then().assertThat().body("success", equalTo(false)).and().statusCode(401);
    }
}
