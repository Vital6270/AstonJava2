/*
Необходимо написать автотесты для сайта mts.by. Суть тестов заключается в проверке
блока «Онлайн пополнение без комиссии»:

1. Проверить название указанного блока;
2. Проверить наличие логотипов платёжных систем;
3. Проверить работу ссылки «Подробнее о сервисе»;
4. Заполнить поля и проверить работу кнопки «Продолжить» (проверяем
только вариант «Услуги связи», номер для теста 297777777)
*/

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
import org.testng.annotations.Test;
import java.time.Duration;



public class Lesson8Test {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }

    // 1. Проверяем название блока «Онлайн пополнение без комиссии»
    @Test
    public void testTitle() {

        WebElement payBox = driver.findElement(By.xpath(
                "//*[@id='pay-section']//*[@class='pay__wrapper']//h2"));

        Assert.assertEquals(payBox.getText(), "Онлайн пополнение\n" +
                "без комиссии");
    }

    // 2. Проверяем наличие логотипов платёжных систем
    @Test
    public void testPayPartners() {

        WebElement visa = driver.findElement(By.xpath(
                "//*[@class='pay__partners']//ul/li[1]")); //не знаю, как здесь избавиться от номера [1]

        WebElement visaVerified = driver.findElement(By.xpath(
                "//*[@class='pay__partners']//ul/li[2]"));

        WebElement masterCard = driver.findElement(By.xpath(
                "//*[@class='pay__partners']//ul/li[3]"));

        WebElement masterCardSecureCode = driver.findElement(By.xpath(
                "//*[@class='pay__partners']//ul/li[4]"));

        WebElement belCard = driver.findElement(By.xpath(
                "//*[@class='pay__partners']//ul/li[5]"));

        Assert.assertTrue(visa.isDisplayed());

        Assert.assertTrue(visaVerified.isDisplayed());

        Assert.assertTrue(masterCard.isDisplayed());

        Assert.assertTrue(masterCardSecureCode.isDisplayed());

        Assert.assertTrue(belCard.isDisplayed());
    }

    //3. Проверяем работу ссылки «Подробнее о сервисе».
    @Test
    public void testLinkIsWorking() throws InterruptedException {

        try {
            WebElement cookieWrapper = driver.findElement(By.className("cookie__wrapper"));
            if (cookieWrapper.isDisplayed()) {
                WebElement acceptCookiesButton = driver.findElement(By.id("cookie-agree"));
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Всплывающее окно с файлами cookie не появилось.");
        }

        WebElement link = driver.findElement(By.xpath(
                "//*[@id='pay-section']//div/a"));

        link.click();

        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.urlContains(
                "/poryadok-oplaty-i-bezopasnost-internet-platezhey"));

        Assert.assertEquals(driver.getTitle(), "Порядок оплаты и безопасность интернет платежей");
    }

    //4. Заполнить поля и проверить работу кнопки «Продолжить» (проверяем
    //только вариант «Услуги связи», номер для теста 297777777).
    @Test
    public void testTextArea() {

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

        Assert.assertTrue(iframe.isDisplayed());
    }
}

