package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.byText;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.Data.*;

public class AuthorizationTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldSendCorrectForm() {

        User infoUser = createUser("active");

        $("[name='login']").setValue(infoUser.getLogin());
        $("[name='password']").setValue(infoUser.getPassword());
        $(".button").click();
        $(byText("Личный кабинет")).shouldBe(Condition.visible);

    }

    @Test
    public void shouldStatusBlocked() {

        User infoUser = createUser("blocked");

        $("[name='login']").setValue(infoUser.getLogin());
        $("[name='password']").setValue(infoUser.getPassword());
        $(".button").click();
        $(byText("Пользователь заблокирован")).shouldBe(Condition.visible);

    }

    @Test
    public void shouldNotRegistered() {

        User withoutInfoUser = withoutInfoUser("active");

        $("[name='login']").setValue(withoutInfoUser.getLogin());
        $("[name='password']").setValue(withoutInfoUser.getPassword());
        $(".button").click();
        $(byText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    @Test
    public void shouldWithWrongLogin() {

        User withoutInfoUser = withoutInfoUser("active");
        var wrongLogin = randomLogin();

        $("[name='login']").setValue(wrongLogin);
        $("[name='password']").setValue(withoutInfoUser.getPassword());
        $(".button").click();
        $(byText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    @Test
    public void shouldWithWrongPassword() {

        User withoutInfoUser = withoutInfoUser("active");
        var wrongPassword = randomPassword();

        $("[name='login']").setValue(wrongPassword);
        $("[name='password']").setValue(withoutInfoUser.getPassword());
        $(".button").click();
        $(byText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }


}
