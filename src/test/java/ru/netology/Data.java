package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class Data {
    private static Faker faker = new Faker(new Locale("en"));

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void sendRequest(User user) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    @Value
    public static class User {
        String login;
        String password;
        String status;
    }

    public static String randomLogin() {
        String login = faker.name().username();
        return login;
    }

    public static String randomPassword() {
        String password = faker.internet().password();
        return password;
    }
    public static User withoutInfoUser(String status) {
        User infoUser = new User(randomLogin(), randomPassword(), status);
        return infoUser;
    }

    public static User createUser(String status) {
        User registeredUser = withoutInfoUser(status);
        sendRequest(registeredUser);
        return registeredUser;
    }


}
