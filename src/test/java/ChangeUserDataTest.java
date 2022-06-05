import client.StepsForDelete;
import client.StepsForPatch;
import client.StepsForPost;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import model.UserCreate;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class ChangeUserDataTest {
    String email = Faker.instance().internet().emailAddress();
    String password = Faker.instance().internet().password();
    String name = Faker.instance().name().firstName();
    String accessToken;

    @After
    public void deleteData() {
        StepsForDelete.doDeleteRequestForDeleteUser(accessToken);
    }

    @Test
    @DisplayName("Можно изменить данные пользователя. Запрос возвращает правильный код ответа 200. Успешный запрос возвращает success:true")
    @Description("Можно изменить данные пользователя. Запрос возвращает правильный код ответа 200. Успешный запрос возвращает success:true")
    public void changeUserCorrectTest() {
        UserCreate userCreate = new UserCreate(email, password, name);
        Response response = StepsForPost.doPostRequestForCreateUser(userCreate);

        accessToken = response.body().as(UserCreate.class).getAccessToken();
        StepsForPatch.doPatchRequestForChangeUser(new UserCreate(Faker.instance().internet().emailAddress(), Faker.instance().internet().password(), Faker.instance().name().firstName()), accessToken)
                .then().assertThat().body("success", equalTo(true)).and().statusCode(200);
    }

    @Test
    @DisplayName("Нельзя изменить данные пользователя без авторизации. Запрос возвращает правильный код ответа 401. Запрос возвращает success:false")
    @Description("Нельзя изменить данные пользователя без авторизации. Запрос возвращает правильный код ответа 401. Запрос возвращает success:false")
    public void changeUserWithoutTokenUncorrectTest() {
        UserCreate userCreate = new UserCreate(email, password, name);
        Response response = StepsForPost.doPostRequestForCreateUser(userCreate);

        accessToken = response.body().as(UserCreate.class).getAccessToken();
        StepsForPatch.doPatchUncorrectRequestForChangeUser(new UserCreate(Faker.instance().internet().emailAddress(), Faker.instance().internet().password(), Faker.instance().name().firstName()))
                .then().assertThat().body("message", equalTo("You should be authorised")).and().statusCode(401);
    }
}
