package tests;

import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

import java.time.Duration;

    public class ClickLogoYandexTest extends BaseTest {

        public static final String Ya_Url = "https://ya.ru/";

    @Test
    public void testLogoYandex()  {

        // Кликаем по лого
        mainPage.clickLogoYandex();

        // Переходим ко второму окну
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // Ждем пока загрузится страница
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.urlToBe(Ya_Url));

        // Проверяем, что открылся правильный URL
        assertEquals(Ya_Url, driver.getCurrentUrl());
    }
}