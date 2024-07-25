package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class OrderPage {
    private WebDriver driver;
    // Поле Имя
    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    // Поле Фамилия
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле Адрес
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле станция метро
    private By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    // Создание динамической строки для станции метро
    private final String metroMenu = "//div[text()='%s']";   //как выбрать меттроо???    //input[@value= '%s'] такой вариант
    // Поле Телефон
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private By nextButton = By.xpath("//button[text()='Далее']");
    // Поле Дата
    private By dateField = By.xpath("//div[@class='react-datepicker-wrapper']/div/input");
    // Поле срок аренды
    private By timeField = By.className("Dropdown-placeholder");
    // Создание динамической строки для срока аренды
    private final String rentTime = "//div[text()='%s']";//как выбрать срок аренды???
    // Поле черный цвет
    private By blackColour = By.id("black");
    // Поле сервый цвет
    private By greyColour = By.id("grey");
    // Поле Комментарий
    private By commentField = By.xpath("//div[@class='Input_InputContainer__3NykH']/input[contains(@class,'Input_Responsible')]");
    // Кнопка Заказать
    private By getOrderButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    // Кнопка Да
    private By yesButton = By.xpath("//button[text()='Да']");
    // Поле заказ одобрен
    private By orderPassResult = By.xpath("//div[text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Далее блок по вводу отдельных полей и нажатию кнопок
    public void inputNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void inputSurnameField(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void inputAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void inputMetroStation(String metroStation) {
        driver.findElement(metroField).click();
        By dynamicMetroXpath = By.xpath(String.format(metroMenu, metroStation));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(dynamicMetroXpath));
        driver.findElement(dynamicMetroXpath).click();
    }

    public void inputPhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void inputDateField(String data) {
        driver.findElement(dateField).sendKeys(data, Keys.ENTER);
    }

    public void inputRentTime(String time) {
        driver.findElement(timeField).click();
        By dynamicRentTime = By.xpath(String.format(rentTime, time));
        driver.findElement(dynamicRentTime).click();
    }

    public void choiceColour(String colour) {
        if (colour.equals("чёрный жемчуг"))
            driver.findElement(blackColour).click();
        else if (colour.equals("серая безысходность")) {
            driver.findElement(greyColour).click();
        }
    }

    public void inputCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickGetOrderButton() {
        driver.findElement(getOrderButton).click();
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    public boolean orderResultISDisplayed() {
        return driver.findElement(orderPassResult).isDisplayed();   // мб в булевую
    }
    // Метод заполняющий форму заявки на первой странице
    public void fillFirstOrderForm(String name, String surname, String address, String metroStation, String phone) {
        inputNameField(name);
        inputSurnameField(surname);
        inputAddressField(address);
        inputMetroStation(metroStation);
        inputPhoneField(phone);
        clickNextButton();
    }
    // Метод заполняющий форму заявки на второй странице
    public void fillSecondOrderForm(String data, String time, String colour, String comment) {
        inputDateField(data);
        inputRentTime(time);
        choiceColour(colour);
        inputCommentField(comment);
        clickGetOrderButton();
        clickYesButton();
    }
}
