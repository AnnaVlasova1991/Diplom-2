import client.StepsForDelete;
import client.StepsForPost;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import model.UserCreate;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserLoginTest {

    String email = Faker.instance().internet().emailAddress();
    String password = Faker.instance().internet().password();
    String name = Faker.instance().name().firstName();
    String accessToken;

    @After
    public void deleteData() {
        StepsForDelete.doDeleteRequestForDeleteUser(accessToken);
    }

    @Test
    @DisplayName("Можно авторизовать пользователя. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    @Description("Можно авторизовать пользователя. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    public void loginUserCorrectTest() {
        UserCreate userCreate = new UserCreate(email, password, name);
        Response response = StepsForPost.doPostRequestForCreateUser(userCreate);

        accessToken = response.body().as(UserCreate.class).getAccessToken();
        StepsForPost.doPostRequestForLoginUser(userCreate, accessToken)
                .then().assertThat().body("success", equalTo(true)).and().statusCode(200);
    }

    @Test
    @DisplayName("Нельзя авторизовать пользователя с некорректным паролем. Запрос возвращает правильный код ответа 401. Успешный запрос возвращает success:true")
    @Description("Нельзя авторизовать пользователя с некорректным паролем. Запрос возвращает правильный код ответа 401. Успешный запрос возвращает success:true")
    public void loginUserUncorrectPasswordTest() {
        UserCreate userCreate = new UserCreate(email, password, name);
        Response response = StepsForPost.doPostRequestForCreateUser(userCreate);

        accessToken = response.body().as(UserCreate.class).getAccessToken();
        StepsForPost.doPostRequestForLoginUser(new UserCreate(email, Faker.instance().internet().password()), accessToken)
                .then().assertThat().body("message", equalTo("email or password are incorrect")).and().statusCode(401);
    }
}
