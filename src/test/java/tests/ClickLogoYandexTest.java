package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.MainPage;
import static org.junit.Assert.*;

import java.time.Duration;

public class ClickLogoYandexTest {
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @Before
    public void setUp() {

        //Запуск на Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Открыть браузер в полноэкранном режиме
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);  // Передаем WebDriver
    }

    @After
    public void tearDown() {
        // Закрываем браузер после тестов
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLogoYandex()  {

        // Кликаем по лого
        mainPage.clickLogoYandex();

        // Переходим ко второму окну
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // Ждем пока загрузится страница
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.urlToBe("https://ya.ru/"));

        // Проверяем, что открылся правильный URL
        assertEquals("https://ya.ru/", driver.getCurrentUrl());
    }
}