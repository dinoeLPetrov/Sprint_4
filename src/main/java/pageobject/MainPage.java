package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы элементов
    private final By questionArrow = By.cssSelector("div.accordion__button");                           // Селектор для стрелочки
    private final By answerText = By.cssSelector("div.accordion__panel");                               // Селектор для текста ответа
    private final By orderButtonTop = By.className("Button_Button__ra12g");                              // Кнопка "Заказать" сверху
    private final By orderButtonBottom = By.cssSelector(".Button_Button__ra12g.Button_UltraBig__UU3Lp"); // Кнопка "Заказать" снизу
    private final By logoScooter = By.cssSelector("a.Header_LogoScooter__3lsAR > img");                  // Лого самоката сверху
    private final By logoYandex = By.cssSelector("a.Header_LogoYandex__3TSOI > img");                    // Лого Яндекса сверху
    private final By CookieButton = By.className("App_CookieButton__3cvqF");                             // Кнопка согласия кук

    // Геттеры
    public By getQuestionArrow() {
        return questionArrow;
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

    // Метод для получения текста ответа
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

    // Метод для клика на куки
    public void clickCookieButton() {
        driver.findElement(CookieButton).click();
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