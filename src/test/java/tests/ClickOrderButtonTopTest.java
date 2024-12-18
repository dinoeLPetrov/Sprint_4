package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.MainPage;
import pageObjects.OrderPage;

import java.time.Duration;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ClickOrderButtonTopTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;
    private WebDriverWait wait;

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

    public ClickOrderButtonTopTest(String name, String lastname, String address, String station, String phone, String color, String rentalPeriod, String comment, String deliveryDate) {
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

    @Before
    public void setUp() {

        //Запуск на Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Открыть браузер в полноэкранном режиме
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testOrderFlow() {

        // Клик по кнопке "Заказать" сверху
        mainPage.clickOrderButtonTop();

        // Заполнение первой формы
        driver.findElement(orderPage.nameField).sendKeys(name);           // Имя
        driver.findElement(orderPage.lastnameField).sendKeys(lastname);   // Фамилия
        driver.findElement(orderPage.addressField).sendKeys(address);     // Адрес
        orderPage.selectMetroStation(station);                            // Выбор станции метро из выпадающего списка
        driver.findElement(orderPage.phoneField).sendKeys(phone);         // Телефон
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

        // Ожидаем появления всплывающего окна
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_ModalHeader__3FDaJ")));

        // Проверяем, что текст всплывающего окна соответствует "Заказ оформлен"
        WebElement popupText = popup.findElement(By.xpath("//div[contains(text(),'Заказ оформлен')]"));
        assertNotNull("Popup with confirmation text is not displayed", popupText);
    }

    @After
    public void tearDown() {
        driver.quit();
   }
}