package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;

    // Локаторы элементов
    public By questionArrow = By.cssSelector("div.accordion__button");                            // Кнопки раскрытия
    public By answerText = By.cssSelector("div.accordion__panel");                                // Блок текста
    public By orderButtonTop = By.className("Button_Button__ra12g");                              // Кнопка "Заказать" сверху
    public By orderButtonBottom = By.cssSelector(".Button_Button__ra12g.Button_UltraBig__UU3Lp"); // Кнопка "Заказать" снизу
    public By logoScooter = By.cssSelector("a.Header_LogoScooter__3lsAR > img");                  // Лого самоката сверху
    public By logoYandex = By.cssSelector("a.Header_LogoYandex__3TSOI > img");                    // Лого Яндекса сверху

    public MainPage(WebDriver driver) {
    this.driver = driver;
    }

    // Методы по нажатию на кнопку "Заказать" (верхнюю или нижнюю)
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }
    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    // Метод для клика по стрелочке
    public void clickQuestionArrow(int index) {
        // Находим все элементы с классом "accordion__button"
        WebElement arrow = driver.findElements(questionArrow).get(index);
        arrow.click();
    }

    // Метод для получения текста ответаgit branch --unset-upstream
    public String getAnswerText(int index) {
        // Находим все элементы с классом "accordion__panel"
        WebElement answer = driver.findElements(answerText).get(index);
        return answer.getText();
    }

    // Метод для проверки видимости ответа
    public boolean isAnswerVisible(int index) {
        WebElement answer = driver.findElements(answerText).get(index);
        return answer.isDisplayed();
    }

    // Метод для клика по лого Самоката
    public void clickLogoScooter() {
        driver.findElement(logoScooter).click();
         }

    // Метод для клика по лого Яндекса
    public void clickLogoYandex() {
        driver.findElement(logoYandex).click();
    }
}