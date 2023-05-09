package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    static void start(){
       app = App.getApp();
        Javalin server = app.start(0);
        int port = server.port();

        baseUrl = "http://localhost:" + port;
    }

    @Test
    void testValidUserData() {
        // Валидные данные пользователя
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        String password = "password";

        // Отправляем POST запрос с валидными данными
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(302);

        // Проверяем, что новый пользователь добавлен в базу данных
        User newUser = new QUser().email.eq(email).findOne();
        assertThat(newUser).isNotNull();
        assertThat(newUser.getFirstName()).isEqualTo(firstName);
        assertThat(newUser.getLastName()).isEqualTo(lastName);
    }

    @Test
    void testInvalidUserData() {
        String firstName = "";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        String password = "password";


        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(422);

        User newUser = new QUser().email.eq(email).findOne();
        assertThat(newUser).isNull();

        String responseBody = response.getBody();
        assertThat(responseBody).contains("Invalid first name");
        assertThat(responseBody).contains("User could not be created");
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @AfterAll
    static void stop() {
        app.stop();
    }
    // END
}
