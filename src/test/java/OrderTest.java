import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.OrderPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String BUTTON_ORDER;
    private final String NAME;
    private final String SURNAME;
    private final String ADDRES;
    private final String METRO_STATION;
    private final String PHONE;
    private final String DATA;
    private final String TIME;
    private final String COLOUR;
    private final String COMMENT;


    public OrderTest(String BUTTON_ORDER, String NAME, String SURNAME, String ADDRES, String METRO_STATION, String PHONE, String DATA, String TIME, String COLOUR, String COMMENT) {
        this.BUTTON_ORDER = BUTTON_ORDER;
        this.NAME = NAME;
        this.SURNAME = SURNAME;
        this.ADDRES = ADDRES;
        this.METRO_STATION = METRO_STATION;
        this.PHONE = PHONE;
        this.DATA = DATA;
        this.TIME = TIME;
        this.COLOUR = COLOUR;
        this.COMMENT = COMMENT;
    }

    @Parameterized.Parameters
    public static Object[][] getTestOrderData() {
        return new Object[][]{
                {"up", "Иван", "Бунин", "Москва, ул. Пушкина, 9", "Спортивная", "+79993483712", "30.07.2024", "пятеро суток", "серая безысходность", "Добавьте зарядку"},
                {"down", "Петр", "Иванов", "Москва, Кремль", "Чистые пруды", "89993483711", "01.08.2024", "сутки", "черный жемчуг", ""},
        };
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @Test
    public void checkOrder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickOrder(BUTTON_ORDER);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstOrderForm(NAME, SURNAME, ADDRES, METRO_STATION, PHONE);
        orderPage.fillSecondOrderForm(DATA, TIME, COLOUR, COMMENT);
        assertTrue("Ошбика при заказе", orderPage.orderResultISDisplayed());
    }

    @After
    public void quit() {
        driver.quit();
    }

}
