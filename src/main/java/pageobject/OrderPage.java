package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    // Локаторы
    // Поле "Имя"
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");

    // Поле "Фамилия"
    private final By lastnameField = By.xpath("//input[@placeholder='* Фамилия']");

    // Поле "Адрес"
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле "Станция метро"
    private final By metroStationField = By.cssSelector(".select-search");

    // Поле "Телефон"
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Поле "Когда привезти самокат"
    private final By dateField = By.cssSelector("input[placeholder='* Когда привезти самокат']");

    // Поле "Срок аренды"
    private final By rentalPeriodField = By.cssSelector("div.Dropdown-root");

    // Поле "Комментарий"
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Кнопка "Заказать"
    private final By finishOrderButton = By.xpath("//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать')]");

    // Кнопка "Да"
    private final By yesConfirmButton = By.xpath("//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да')]");

    // Ожидание всплывающего окна
    private final By popupLocator = By.className("Order_ModalHeader__3FDaJ");

    // Проверка текста в всплывающем окне
    private final By popupTextLocator = By.xpath("//div[contains(text(),'Заказ оформлен')]");

    // Геттеры для локаторов
    public By getNameField() {
        return nameField;
    }

    public By getLastnameField() {
        return lastnameField;
    }

    public By getAddressField() {
        return addressField;
    }

    public By getPhoneField() {
        return phoneField;
    }

    // Методы
    // Метод для ввода станции метро и выбора из выпадающего списка
    public void selectMetroStation(String station) {

        // Ожидаем, пока появится список станций
        WebElement stationField = wait.until(ExpectedConditions.elementToBeClickable(metroStationField));
        stationField.click();
        // Ожидаем появления списка и выбираем станцию из выпадающего списка
        By stationOption = By.xpath("//div[@class='Order_Text__2broi' and text()='" + station + "']");
        WebElement selectedStation = wait.until(ExpectedConditions.elementToBeClickable(stationOption));
        selectedStation.click();  // Кликаем по нужной станции
    }

    // Метод для выбора даты в календаре
    public void selectDeliveryDate(String date) {

        // Кликаем на поле, чтобы открыть календарь
        WebElement dateFieldElement = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        dateFieldElement.click();

        // Разбираем дату на день, месяц и год
        String[] dateParts = date.split("\\.");
        String day = dateParts[0]; // День

        // Находим все доступные дни в календаре
        WebElement dayElement = driver.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day') and text() = '" + day + "']"));
        dayElement.click(); // Кликаем по нужному дню
    }

    // Метод для выбора срока аренды
    public void selectRentTerm(String rentalPeriod) {

        // Ожидаем, пока появится список
        WebElement stationField = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodField));
        stationField.click();
        // Ожидаем появления списка и выбираем
        By stationOption = By.xpath("//div[@class='Dropdown-option' and text()='" + rentalPeriod + "']");
        WebElement selectedStation = wait.until(ExpectedConditions.elementToBeClickable(stationOption));
        selectedStation.click();  // Кликаем по нужному варианту
      }

    // Метод для выбора цвета
    public void selectColor(String color) {

        // Выбор нужного чекбокса по цвету
        By colorCheckbox = By.xpath("//label[contains(text(), '" + color + "')]");

        // Ожидаем появления чекбокса и кликаем по нему
        WebElement colorOption = wait.until(ExpectedConditions.elementToBeClickable(colorCheckbox));
        colorOption.click();  // Если чекбокс не выбран, кликаем по нему
      }

    // Метод для комментария
    public void commentField(String comment) {
        // Ожидаем, пока поле "Комментарий курьеру" станет доступным
        WebElement commentTextArea = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        // Очищаем поле и вводим текст
        commentTextArea.clear();
        commentTextArea.sendKeys(comment);
    }

    // Метод для клика на кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Метод для клика на кнопку "Заказать" при оформлении
    public void clickFinishOrderButton() {
        driver.findElement(finishOrderButton).click();
    }

    // Метод для клика на кнопку "Да"
    public void clickYesButton() {
        driver.findElement(yesConfirmButton).click();
    }

    // Метод для ожидания появления всплывающего окна
    public WebElement waitForPopup() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(popupLocator));
    }

    // Метод для проверки текста в всплывающем окне
    public boolean isPopupTextCorrect(String expectedText) {
        WebElement popup = waitForPopup();
        WebElement popupText = popup.findElement(popupTextLocator);
        return popupText.getText().contains(expectedText);
    }
}