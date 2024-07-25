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
    private final String buttonOrder;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String data;
    private final String time;
    private final String colour;
    private final String comment;
    private final String scooterWebsite = "https://qa-scooter.praktikum-services.ru/";


    public OrderTest(String buttonOrder, String name, String surname, String address, String metroStation, String phone, String data, String time, String colour, String comment) {
        this.buttonOrder = buttonOrder;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.data = data;
        this.time = time;
        this.colour = colour;
        this.comment = comment;
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
        driver.get(scooterWebsite);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @Test
    public void checkOrder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickOrder(buttonOrder);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstOrderForm(name, surname, address, metroStation, phone);
        orderPage.fillSecondOrderForm(data, time, colour, comment);
        assertTrue("Ошбика при заказе", orderPage.orderResultISDisplayed());
    }

    @After
    public void quit() {
        driver.quit();
    }

}
