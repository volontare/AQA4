import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.Keys.*;

public class Tests {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }


    @Test
    void shouldTestSuccessOrderWithValidData() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована"))
                .shouldHave(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(text(date));
    }

    @Test
    void shouldTestOrderWhenInvalidCity() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Тольятти");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Доставка в выбранный город недоступна")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestOrderWhenInvalidCity2() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Samara");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Доставка в выбранный город недоступна")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestOrderWhenNoCity() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Поле обязательно для заполнения")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestOrderWhenInvalidTel() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("89999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Телефон указан неверно")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestOrderWhenInvalidTel2() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+799999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Телефон указан неверно")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestOrderWhenNoTel() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Поле обязательно для заполнения")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestOrderWhenInvalidName() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Mariya Ivanova");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Имя и Фамилия указаные неверно")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestOrderWhenInvalidName2() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Анна=Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Имя и Фамилия указаные неверно")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestOrderWhenValidName() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Анна-Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована"))
                .shouldHave(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(text(date));
    }

    @Test
    void shouldTestOrderWhenNoAgreement() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $$("button").find(Condition.exactText("Забронировать")).click();
        String text = $(".checkbox__text").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", text);
    }

    @Test
    void shouldTestOrderWhenMeetingDateUnder3() {
        String date = LocalDate.now().plusDays(2).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Заказ на выбранную дату невозможен")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestOrderWhenMeetingDateOver3() {
        String date = LocalDate.now().plusDays(366).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована"))
                .shouldHave(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(text(date));
    }

    @Test
    void shouldTestOrderWithDropdownList() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Са");
        $(".input__menu").find(withText("Самара")).click();
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(DELETE, date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована"))
                .shouldHave(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(text(date));
    }

    @Test
    void shouldTestOrderWithCalendar() {
        String date = LocalDate.now().plusDays(7).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $(".icon-button__content").click();
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id='name'] .input__control").setValue("Мария Иванова");
        $("[data-test-id='phone'] .input__control").setValue("+79999999999");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована"))
                .shouldHave(Condition.visible, Duration.ofSeconds(15));
    }
}