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

public class ClickLogoScooterTest {
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
    public void testLogoScooter()  {

        // Кликаем по лого
        mainPage.clickLogoScooter();

        // Ожидаем загрузки главной страницы (например, ждем, что URL изменится или появляется элемент главной страницы)
        wait.until(ExpectedConditions.urlToBe("https://qa-scooter.praktikum-services.ru/"));

        // Проверяем, что текущий URL соответствует главной странице
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Переход на главную страницу не произошел", "https://qa-scooter.praktikum-services.ru/", currentUrl);
    }
}