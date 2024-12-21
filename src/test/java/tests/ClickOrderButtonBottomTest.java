package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ClickOrderButtonBottomTest extends BaseTest{

    // Параметры для теста (наборы данных)
    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {"Иван", "Иванов", "МСКБ Невский, 65Б 123", "Бульвар Рокоссовского", "+79995556644", "чёрный жемчуг", "сутки", "5 подъезд, 2 этаж, домофон работает", "25.12.2024"},
                {"Петр", "Петров", "СПб, Индустриальный 17, 65", "Черкизовская", "+79115558833", "серая безысходность", "трое суток", "5th entrance, 2nd floor, intercom is working", "20.12.2024"}
        };
    }

    // Параметры из набора данных
    private final String name;
    private final String lastname;
    private final String address;
    private final String station;
    private final String phone;
    private final String color;
    private final String rentalPeriod;
    private final String comment;
    private final String deliveryDate;

    public ClickOrderButtonBottomTest(String name, String lastname, String address, String station, String phone, String color, String rentalPeriod, String comment, String deliveryDate) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.color = color;
        this.rentalPeriod = rentalPeriod;
        this.comment = comment;
        this.deliveryDate = deliveryDate;
    }

    @Test
    public void testOrderFlow()  {

        // Кликаем на всплывашку о куках
        mainPage.clickCookieButton();

        // Клик по кнопке "Заказать" снизу
        mainPage.clickOrderButtonBottom();

        // Заполнение первой формы
        driver.findElement(orderPage.getNameField()).sendKeys(name);           // Имя
        driver.findElement(orderPage.getLastnameField()).sendKeys(lastname);   // Фамилия
        driver.findElement(orderPage.getAddressField()).sendKeys(address);     // Адрес
        orderPage.selectMetroStation(station);                                 // Выбор станции метро из выпадающего списка
        driver.findElement(orderPage.getPhoneField()).sendKeys(phone);         // Телефон

        // Кнопка Далее
        orderPage.clickNextButton();

        // Заполнение второй формы
        orderPage.selectDeliveryDate(deliveryDate);                    // Выбираем дату из календаря
        orderPage.selectRentTerm(rentalPeriod);                        // Выбор срока аренды из выпадающего списка
        orderPage.selectColor(color);                                  // Выбираем цвет самоката
        orderPage.commentField(comment);                               // Вводим комментарий

        // Кнопка Заказать
        orderPage.clickFinishOrderButton();

        // Кнопка Да
        orderPage.clickYesButton();

        // Ожидаем появления всплывающего окна и проверяем текст
        assertTrue("Popup with confirmation text is not displayed",
                orderPage.isPopupTextCorrect("Заказ оформлен"));
    }
}