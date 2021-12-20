package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.Data.*;

public class AuthorizationTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSendCorrectForm(){

        User infoUser = createUser("active");

        $("[name='login']").setValue(Data.randomLogin());
        $("[name='password']").setValue(Data.randomPassword());
        //$(".button").click();
        $$("button").find(Condition.text("Продолжить")).click();

    }


}
