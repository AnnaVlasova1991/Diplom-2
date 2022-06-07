import client.StepsForPost;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import model.UserCreate;
import org.junit.After;
import org.junit.Test;
import io.restassured.response.Response;
import client.StepsForDelete;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {

    String email = Faker.instance().internet().emailAddress();
    String password = Faker.instance().internet().password();
    String name = Faker.instance().name().firstName();
    String accessToken;

    @After
    public void deleteData() {
        StepsForDelete.doDeleteRequestForDeleteUser(accessToken);
    }

    @Test
    @DisplayName("Можно создать уникального пользователя. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    @Description("Можно создать уникального пользователя. Запрос возвращает правильный код ответа. Успешный запрос возвращает success:true")
    public void createUserCorrectTest() {
        Response response = StepsForPost.doPostRequestForCreateUser(new UserCreate(email, password, name));
                response.then().assertThat().body("success", equalTo(true)).and().statusCode(200);

        accessToken = response.body().as(UserCreate.class).getAccessToken();
    }

    @Test
    @DisplayName("Нельзя создать пользователя, который уже зарегистрирован. Запрос возвращает код ответа 403. Запрос возвращает success:false")
    @Description("Нельзя создать пользователя, который уже зарегистрирован. Запрос возвращает код ответа 403. Запрос возвращает success:false")
    public void createExistUserUncorrectTest() {
        UserCreate userCreate = new UserCreate(email, password, name);
        Response response = StepsForPost.doPostRequestForCreateUser(userCreate);
        StepsForPost.doPostRequestForCreateUser(userCreate)
                .then().assertThat().body("success", equalTo(false)).and().statusCode(403);

        accessToken = response.body().as(UserCreate.class).getAccessToken();
    }

    @Test
    @DisplayName("Нельзя создать пользователя с незаполненным обязательным полем. Запрос возвращает код ответа 403. Запрос возвращает success:false")
    @Description("Нельзя создать пользователя с незаполненным обязательным полем. Запрос возвращает код ответа 403. Запрос возвращает success:false")
    public void createUserWithoutFieldUncorrectTest() {
        Response response = StepsForPost.doPostRequestForCreateUser(new UserCreate(email, "", name));
        response.then().assertThat().body("message", equalTo("Email, password and name are required fields")).and().statusCode(403);

        accessToken = "";
    }
}
