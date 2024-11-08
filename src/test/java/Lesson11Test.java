import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

/*
 Продолжим работу над блоком «Онлайн пополнение без комиссии» сайта mts.by.
 1. Проверить надписи в незаполненных полях каждого варианта оплаты услуг: услуги связи, домашний интернет,
 рассрочка, задолженность.
 */

public class Lesson11Test {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }

    // Проверяем надписи в незаполненных полях каждого варианта оплаты услуг: услуги связи, домашний интернет,
    // рассрочка, задолженность.
    @DataProvider(name = "placeholderData")
    public Object[][] placeholderData() {
        return new Object[][]{
                // {Класс кнопки, XPath элемента, id для телефона, id для суммы, id для email, ожидаемые значения}
                {"select__header", "//*[@id='pay-section']//li[1]/p", "connection-phone", "connection-sum", "connection-email",
                        new String[]{"Номер телефона", "Сумма", "E-mail для отправки чека"}}, // Услуги связи
                {"select__header", "//*[@id='pay-section']//li[2]/p", "internet-phone", "internet-sum", "internet-email",
                        new String[]{"Номер абонента", "Сумма", "E-mail для отправки чека"}}, // Домашний интернет
                {"select__header", "//*[@id='pay-section']//li[3]/p", "score-instalment", "instalment-sum", "instalment-email",
                        new String[]{"Номер счета на 44", "Сумма", "E-mail для отправки чека"}}, // Рассрочка
                {"select__header", "//*[@id='pay-section']//li[4]/p", "score-arrears", "arrears-sum", "arrears-email",
                        new String[]{"Номер счета на 2073", "Сумма", "E-mail для отправки чека"}}  // Задолженность
        };
    }

    @Test(dataProvider = "placeholderData")
    @Epic("MTS main page")
    @Feature("Placeholders")
    @Description("Этот тест проверяет надписи в незаполненных полях каждого варианта оплаты услуг")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Vitaliy Kudel")
    @Link("https://www.mts.by/")
    @Issue("T-1")
    public void testPlaceholders(String buttonClass, String xpath, String phoneId, String sumId, String emailId, String[] expectedPlaceholders) {

        try {
            WebElement cookieWrapper = driver.findElement(By.className("cookie__wrapper"));
            if (cookieWrapper.isDisplayed()) {
                WebElement acceptCookiesButton = driver.findElement(By.id("cookie-agree"));
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Всплывающее окно с файлами cookie не появилось.");
        }

        WebElement button = driver.findElement(By.className(buttonClass));
        button.click();

        WebElement option = driver.findElement(By.xpath(xpath));
        option.click();

        WebElement inputPhoneNumber = driver.findElement(By.id(phoneId));
        String phonePlaceholderText = inputPhoneNumber.getAttribute("placeholder");
        Assert.assertEquals(phonePlaceholderText, expectedPlaceholders[0]);

        WebElement inputSum = driver.findElement(By.id(sumId));
        String sumText = inputSum.getAttribute("placeholder");
        Assert.assertEquals(sumText, expectedPlaceholders[1]);

        WebElement inputEmail = driver.findElement(By.id(emailId));
        String emailText = inputEmail.getAttribute("placeholder");
        Assert.assertEquals(emailText, expectedPlaceholders[2]);
    }

    // 2. Для варианта «Услуги связи» заполнить поля в соответствии с пререквизитами из предыдущей темы,
    // нажать кнопку «Продолжить» и в появившемся окне проверить корректность отображения суммы
    // (в том числе на кнопке), номера телефона, а также надписей в незаполненных полях для ввода
    // реквизитов карты, наличие иконок платёжных систем.


    // Проверяем сумму вверху фрейма
    @Test
    @Epic("MTS main page")
    @Feature("Total sum")
    @Description("Этот тест проверяет сумму вверху фрейма")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Vitaliy Kudel")
    @Link("https://www.mts.by/")
    @Issue("T-2")
    public void testHeaderSum() {

        try {
            WebElement cookieWrapper = driver.findElement(By.className("cookie__wrapper"));
            if (cookieWrapper.isDisplayed()) {
                WebElement acceptCookiesButton = driver.findElement(By.id("cookie-agree"));
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Всплывающее окно с файлами cookie не появилось.");
        }

        WebElement phoneBox = driver.findElement(By.id("connection-phone"));
        phoneBox.sendKeys("297777777");

        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.sendKeys("10");

        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys("test1@test.com");

        WebElement button = driver.findElement(By.xpath(
                "//*[@id='pay-connection']/button"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bepaid-iframe")));

        driver.switchTo().frame(iframe);

        WebElement headerSum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//section/div/div/div[1]/span[1]")));

        Assert.assertEquals(headerSum.getText(),"10.00 BYN");
    }

    // Проверяем сумму внизу на кнопке
    @Test
    @Epic("MTS main page")
    @Feature("Total sum")
    @Description("Этот тест проверяет сумму внизу на кнопке")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Vitaliy Kudel")
    @Link("https://www.mts.by/")
    @Issue("T-3")
    public void testButtonSum() {

        try {
            WebElement cookieWrapper = driver.findElement(By.className("cookie__wrapper"));
            if (cookieWrapper.isDisplayed()) {
                WebElement acceptCookiesButton = driver.findElement(By.id("cookie-agree"));
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Всплывающее окно с файлами cookie не появилось.");
        }

        WebElement phoneBox = driver.findElement(By.id("connection-phone"));
        phoneBox.sendKeys("297777777");

        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.sendKeys("10");

        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys("test1@test.com");

        WebElement button = driver.findElement(By.xpath(
                "//*[@id='pay-connection']/button"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bepaid-iframe")));

        driver.switchTo().frame(iframe);

        WebElement buttonSum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("colored")));

        Assert.assertEquals(buttonSum.getText(),"Оплатить 10.00 BYN");
    }

    // Проверяем номер телефона вверху фрейма
    @Test
    @Epic("MTS main page")
    @Feature("Phone number")
    @Description("Этот тест проверяет номер телефона вверху фрейма")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Vitaliy Kudel")
    @Link("https://www.mts.by/")
    @Issue("T-4")
    public void testPhoneNumber() {

        try {
            WebElement cookieWrapper = driver.findElement(By.className("cookie__wrapper"));
            if (cookieWrapper.isDisplayed()) {
                WebElement acceptCookiesButton = driver.findElement(By.id("cookie-agree"));
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Всплывающее окно с файлами cookie не появилось.");
        }

        WebElement phoneBox = driver.findElement(By.id("connection-phone"));
        phoneBox.sendKeys("297777777");

        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.sendKeys("10");

        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys("test1@test.com");

        WebElement button = driver.findElement(By.xpath(
                "//*[@id='pay-connection']/button"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bepaid-iframe")));

        driver.switchTo().frame(iframe);

        WebElement headerSum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//section/div/div/div[2]/span")));
        Assert.assertEquals(headerSum.getText(),"Оплата: Услуги связи Номер:375297777777");
    }

    // Проверяем надписи в незаполненных полях фрейма для оплаты
    @Test
    @Epic("MTS main page")
    @Feature("Payment texts")
    @Description("Этот тест проверяет надписи в незаполненных полях фрейма для оплаты")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Vitaliy Kudel")
    @Link("https://www.mts.by/")
    @Issue("T-5")
    public void testTextsOfPayment() {

        try {
            WebElement cookieWrapper = driver.findElement(By.className("cookie__wrapper"));
            if (cookieWrapper.isDisplayed()) {
                WebElement acceptCookiesButton = driver.findElement(By.id("cookie-agree"));
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Всплывающее окно с файлами cookie не появилось.");
        }

        WebElement phoneBox = driver.findElement(By.id("connection-phone"));
        phoneBox.sendKeys("297777777");

        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.sendKeys("10");

        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys("test1@test.com");

        WebElement button = driver.findElement(By.xpath(
                "//*[@id='pay-connection']/button"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bepaid-iframe")));

        driver.switchTo().frame(iframe);

        WebElement cardNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//form/div[1]/div[1]/app-input/div/div/div[1]/label")));
        String cardNumberText = cardNumber.getText();

        WebElement validTo = driver.findElement(By.xpath(
                "//div[2]/div[1]/app-input/div/div/div[1]/label"));
        String validToText = validTo.getText();

        WebElement inputCVC = driver.findElement(By.xpath(
                "//div[2]/div[3]/app-input/div/div/div[1]/label"));
        String cvcText = inputCVC.getText();

        WebElement inputName = driver.findElement(By.xpath(
                "//div[1]/div[3]/app-input/div/div/div[1]/label"));
        String nameText = inputName.getText();

        Assert.assertEquals(cardNumberText, "Номер карты");
        Assert.assertEquals(validToText, "Срок действия");
        Assert.assertEquals(cvcText, "CVC");
        Assert.assertEquals(nameText, "Имя держателя (как на карте)");
    }

    // Проверяем наличие иконок платежных систем во фрейме оплаты
    @Test
    @Epic("MTS main page")
    @Feature("Payment texts")
    @Description("Этот тест проверяет надписи в незаполненных полях фрейма для оплаты")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Vitaliy Kudel")
    @Link("https://www.mts.by/")
    @Issue("T-5")
    public void testFramePayPartners() {

        try {
            WebElement cookieWrapper = driver.findElement(By.className("cookie__wrapper"));
            if (cookieWrapper.isDisplayed()) {
                WebElement acceptCookiesButton = driver.findElement(By.id("cookie-agree"));
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Всплывающее окно с файлами cookie не появилось.");
        }

        WebElement phoneBox = driver.findElement(By.id("connection-phone"));
        phoneBox.sendKeys("297777777");

        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.sendKeys("10");

        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys("test1@test.com");

        WebElement button = driver.findElement(By.xpath(
                "//*[@id='pay-connection']/button"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bepaid-iframe")));

        driver.switchTo().frame(iframe);

        WebElement visa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//app-input//div/div/img[1]")));

        WebElement masterCard = driver.findElement(By.xpath(
                "//app-input//div/div/img[2]"));

        WebElement belCard = driver.findElement(By.xpath(
                "//app-input//div/div/img[3]"));

        WebElement mir = driver.findElement(By.xpath(
                "//div[2]/div/div/div/img[1]"));

        Assert.assertTrue(visa.isDisplayed());
        Assert.assertTrue(masterCard.isDisplayed());
        Assert.assertTrue(belCard.isDisplayed());
        Assert.assertTrue(mir.isDisplayed());
    }
}