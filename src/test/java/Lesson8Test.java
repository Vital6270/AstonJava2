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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;



public class Lesson8Test {

    // 1. Проверяем название блока «Онлайн пополнение без комиссии»

    @Test
    public void testTitle() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement payBox = driver.findElement(By.xpath(
                "//*[@id='pay-section']/div/div/div[2]/section/div/h2"));

        Assert.assertEquals(payBox.getText(), "Онлайн пополнение\n" +
                "без комиссии");

        driver.quit();
    }

    // 2. Проверяем наличие логотипов платёжных систем

    @Test
    public void testPayPartners() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        WebElement visa = driver.findElement(By.xpath(
                "//*[@id='pay-section']/div/div/div[2]/section/div/div[2]/ul/li[1]"));

        WebElement visaVerified = driver.findElement(By.xpath(
                "//*[@id='pay-section']/div/div/div[2]/section/div/div[2]/ul/li[2]/img"));

        WebElement masterCard = driver.findElement(By.xpath(
                "//*[@id='pay-section']/div/div/div[2]/section/div/div[2]/ul/li[3]/img"));

        WebElement masterCardSecureCode = driver.findElement(By.xpath(
                "//*[@id='pay-section']/div/div/div[2]/section/div/div[2]/ul/li[4]/img"));

        WebElement belCard = driver.findElement(By.xpath(
                "//*[@id='pay-section']/div/div/div[2]/section/div/div[2]/ul/li[5]/img"));

        Assert.assertTrue(visa.isDisplayed());

        Assert.assertTrue(visaVerified.isDisplayed());

        Assert.assertTrue(masterCard.isDisplayed());

        Assert.assertTrue(masterCardSecureCode.isDisplayed());

        Assert.assertTrue(belCard.isDisplayed());

        driver.quit();
    }

    //3. Проверяем работу ссылки «Подробнее о сервисе».
    //Если появляется окно с Cookies, то тест фейлится. Не знаю пока, как с этим бороться (добавлять if clause?)

    @Test
    public void testLinkIsWorking() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        WebElement link = driver.findElement(By.xpath(
                "//*[@id='pay-section']/div/div/div[2]/section/div/a"));

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        link.click();

        Thread.sleep(2000);

        Assert.assertEquals(driver.getTitle(), "Порядок оплаты и безопасность интернет платежей");

        driver.quit();
    }

    //4. Заполнить поля и проверить работу кнопки «Продолжить» (проверяем
    //только вариант «Услуги связи», номер для теста 297777777).
    //Если появляется окно с Cookies, то тест фейлится. Не знаю пока, как с этим бороться (добавлять if clause?)

    @Test
    public void testTextArea() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        
        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();

        WebElement phoneBox = driver.findElement(By.id("connection-phone"));
        phoneBox.sendKeys("297777777");

        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.sendKeys("10");

        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys("test1@test.com");

        WebElement button = driver.findElement(By.xpath(
                "//*[@id='pay-connection']/button"));
        button.click();

        Thread.sleep(3000);

        WebElement iframe = driver.findElement(By.className("bepaid-iframe"));

        driver.switchTo().frame(iframe);

        WebElement element = driver.findElement(By.className("ng-star-inserted"));

        Assert.assertTrue(element.isDisplayed());

        driver.quit();
    }
}

