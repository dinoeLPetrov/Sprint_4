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

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ClickQuestionsAboutImportantTest {
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    // Индекс стрелочки, передаваемый в тест
    private final int index;

    public ClickQuestionsAboutImportantTest(int index) {
        this.index = index;
    }

    // Параметризация - возвращаем индексы стрелочек для проверки
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0}, {1}, {2}, {3}, {4} // Индексы стрелочек для тестирования
        });
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
    }

    @After
    public void tearDown() {
        // Закрываем браузер после тестов
        if (driver != null) {
            driver.quit();
        }
    }

    // Параметризированный тест для проверки разных стрелочек
    @Test
    public void testQuestionDropdown()  {

        // Кликаем на всплывашку о куках
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();

        // Кликаем на стрелочку по индексу
        mainPage.clickQuestionArrow(index);

        // Ожидаем раскрытия карточки
        WebElement isAnswerVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.questionArrow));

        // Проверяем, что текст ответа отображается
        assertTrue("Ответ не отображается после клика по стрелке с индексом " + index, mainPage.isAnswerVisible(index));

        // Получаем текст ответа и проверяем, что он не пустой
        String answerText = mainPage.getAnswerText(index);
        assertNotNull("Ответ на вопрос с индексом " + index + " пустой.", answerText);
    }
}