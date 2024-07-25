package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class MainPage {
    private WebDriver driver;


    //Кнопка Заказать вверху страницы
    private By orderButtonUp = By.xpath("//button[@class='Button_Button__ra12g' and text()='Заказать']");
    //Кнопка Заказать внизу страницы
    private By orderButtonDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");
    //Кнопка cookie
    private By cookieButton = By.xpath("//button[text()='да все привыкли']");
    //Список элементов "Вопросы о важном"
    private final String questionXpath = "//div[@id='accordion__heading-%s']";    // подумать как легче, %s для параметров
    //Список ответов
    private final String answerXpath = "//div[contains(@id, 'accordion__panel')]/p[text()='%s']"; // подумать как легче, %s для параметров


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //Метод кликает по кнопке согласия с куками
    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    //Метод кликает по верхней кнопке Заказать
    public void clickOrderButtonUp() {
        driver.findElement(orderButtonUp).click();
    }

    //Метод кликает по нижней кнопке Заказать
    public void clickOrderButtonDown() {
        WebElement element = driver.findElement(orderButtonDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderButtonDown).click();
    }
    //Метод кликает по нужному вопросу
    public void clickQuestion(int index) {
        By pathQuestionDinamic = By.xpath(String.format(questionXpath, index));
        WebElement elementQuestion = driver.findElement(pathQuestionDinamic);
        new WebDriverWait(driver, ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(elementQuestion));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementQuestion);
        elementQuestion.click();
    }
    //Метод получает ответ
    public boolean answerIsDisplayed(String answer) {
        WebElement element = driver.findElement(By.xpath(String.format(answerXpath, answer)));
        return element.isDisplayed();
    }
    //Метод нажимае верхнюю или нижнюю кнопку заказа, в зависимости от переданного аргумента
    public void clickOrder(String button) {     // для параметра с кнопкой
        if (button.equals("up"))
            clickOrderButtonUp();
        else if (button.equals("down")) {
            clickOrderButtonDown();
        }

    }

}
