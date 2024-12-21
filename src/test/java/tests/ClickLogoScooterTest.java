package tests;

import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.*;

public class ClickLogoScooterTest extends BaseTest {

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Test
    public void testLogoScooter()  {

        // Кликаем по лого
        mainPage.clickLogoScooter();

        // Ожидаем загрузки главной страницы
        wait.until(ExpectedConditions.urlToBe(BASE_URL));

        // Проверяем, что текущий URL соответствует главной странице
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Переход на главную страницу не произошел", BASE_URL, currentUrl);
    }
}