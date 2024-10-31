import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 Продолжим работу над блоком «Онлайн пополнение без комиссии» сайта mts.by.
 1. Проверить надписи в незаполненных полях каждого варианта оплаты услуг: услуги связи, домашний интернет,
 рассрочка, задолженность.
 */

public class TestLesson9 {

    // Проверяем надписи в незаполненных полях для варианта "Услуги связи"

    @Test
    public void testCommunicationServices() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.mts.by/");

        driver.manage().window().maximize();

        WebElement inputPhoneNumber = driver.findElement(By.id("connection-phone"));
        String PhonePlaceholderText = inputPhoneNumber.getAttribute("placeholder");

        WebElement inputSum = driver.findElement(By.id("connection-sum"));
        String sumText = inputSum.getAttribute("placeholder");

        WebElement inputEmail = driver.findElement(By.id("connection-email"));
        String emailText = inputEmail.getAttribute("placeholder");

        Assert.assertEquals(PhonePlaceholderText, "Номер телефона");

        Assert.assertEquals(sumText, "Сумма");

        Assert.assertEquals(emailText, "E-mail для отправки чека");

        driver.quit();
    }

    // Проверяем надписи в незаполненных полях для варианта "Домашний интернет"

    @Test
    public void testHomeInternet() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.mts.by/");

        driver.manage().window().maximize();

        WebElement button = driver.findElement(By.className("select__header"));
        button.click();

        WebElement homeInternet = driver.findElement(By.xpath(
                "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[2]/p"));
        homeInternet.click();

        WebElement inputPhoneNumber = driver.findElement(By.id("internet-phone"));
        String PhonePlaceholderText = inputPhoneNumber.getAttribute("placeholder");

        WebElement inputSum = driver.findElement(By.id("internet-sum"));
        String sumText = inputSum.getAttribute("placeholder");

        WebElement inputEmail = driver.findElement(By.id("internet-email"));
        String emailText = inputEmail.getAttribute("placeholder");

        Assert.assertEquals(PhonePlaceholderText, "Номер абонента");

        Assert.assertEquals(sumText, "Сумма");

        Assert.assertEquals(emailText, "E-mail для отправки чека");

        driver.quit();
    }

    // Проверяем надписи в незаполненных полях для варианта "Рассрочка"

    @Test
    public void testInstalment() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();

        WebElement button = driver.findElement(By.className("select__header"));
        button.click();

        WebElement instalment = driver.findElement(By.xpath(
                "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[3]/p"));
        instalment.click();

        WebElement instalmentNumber = driver.findElement(By.id("score-instalment"));
        String instalmentPlaceholderText = instalmentNumber.getAttribute("placeholder");

        WebElement inputSum = driver.findElement(By.id("instalment-sum"));
        String sumText = inputSum.getAttribute("placeholder");

        WebElement inputEmail = driver.findElement(By.id("instalment-email"));
        String emailText = inputEmail.getAttribute("placeholder");

        Assert.assertEquals(instalmentPlaceholderText, "Номер счета на 44");

        Assert.assertEquals(sumText, "Сумма");

        Assert.assertEquals(emailText, "E-mail для отправки чека");

        driver.quit();
    }

    // Проверяем надписи в незаполненных полях для варианта "Задолженность"

    @Test
    public void testDebt() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.mts.by/");

        driver.manage().window().maximize();

        WebElement button = driver.findElement(By.className("select__header"));
        button.click();

        WebElement debt = driver.findElement(By.xpath(
                "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[4]/p"));
        debt.click();

        WebElement scoreNumber = driver.findElement(By.id("score-arrears"));
        String scorePlaceholderText = scoreNumber.getAttribute("placeholder");

        WebElement inputSum = driver.findElement(By.id("arrears-sum"));
        String sumText = inputSum.getAttribute("placeholder");

        WebElement inputEmail = driver.findElement(By.id("arrears-email"));
        String emailText = inputEmail.getAttribute("placeholder");

        Assert.assertEquals(scorePlaceholderText, "Номер счета на 2073");

        Assert.assertEquals(sumText, "Сумма");

        Assert.assertEquals(emailText, "E-mail для отправки чека");

        driver.quit();
    }

    // 2. Для варианта «Услуги связи» заполнить поля в соответствии с пререквизитами из предыдущей темы,
    // нажать кнопку «Продолжить» и в появившемся окне проверить корректность отображения суммы
    // (в том числе на кнопке), номера телефона, а также надписей в незаполненных полях для ввода
    // реквизитов карты, наличие иконок платёжных систем.


    // Проверяем сумму вверху фрейма

    @Test
    public void testHeaderSum() throws InterruptedException {

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

        WebElement headerSum = driver.findElement(By.xpath(
                "//div/div/div/app-payment-container/section/div/div/div[1]/span[1]"));

        Assert.assertEquals(headerSum.getText(),"10.00 BYN");

        driver.quit();
    }

    // Проверяем сумму внизу на кнопке

    @Test
    public void testButtonSum() throws InterruptedException {

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

        WebElement buttonSum = driver.findElement(By.className("colored"));

        Assert.assertEquals(buttonSum.getText(),"Оплатить 10.00 BYN");

        driver.quit();
    }

    // Проверяем номер телефона вверху фрейма
    // Если появляется окно с Cookies, тест фейлится

    @Test
    public void testPhoneNumber() throws InterruptedException {

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

        WebElement headerSum = driver.findElement(By.xpath(
                "//div/div/div/app-payment-container/section/div/div/div[2]/span"));
        Assert.assertEquals(headerSum.getText(),"Оплата: Услуги связи Номер:375297777777");

        driver.quit();
    }

    // Проверяем надписи в незаполненных полях фрейма для оплаты
    // Если появляется окно с Cookies, тест фейлится

    @Test
    public void testTextsOfPayment() throws InterruptedException {

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

        WebElement cardNumber = driver.findElement(By.xpath(
                "//app-card-input/form/div[1]/div[1]/app-input/div/div/div[1]/label"));
        String cardNumberText = cardNumber.getText();

        WebElement validTo = driver.findElement(By.xpath(
                "//app-card-input/form/div[1]/div[2]/div[1]/app-input/div/div/div[1]/label"));
        String validToText = validTo.getText();

        WebElement inputCVC = driver.findElement(By.xpath(
                "//app-card-input/form/div[1]/div[2]/div[3]/app-input/div/div/div[1]/label"));
        String cvcText = inputCVC.getText();

        WebElement inputName = driver.findElement(By.xpath(
                "//app-card-input/form/div[1]/div[3]/app-input/div/div/div[1]/label"));
        String nameText = inputName.getText();

        Assert.assertEquals(cardNumberText, "Номер карты");

        Assert.assertEquals(validToText, "Срок действия");

        Assert.assertEquals(cvcText, "CVC");

        Assert.assertEquals(nameText, "Имя держателя (как на карте)");

        driver.quit();
    }

    // Проверяем наличие иконок платежных систем во фрейме оплаты

    @Test
    public void testFramePayPartners() throws InterruptedException {

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

        WebElement visa = driver.findElement(By.xpath(
                "//app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]/div/div/img[1]"));

        WebElement masterCard = driver.findElement(By.xpath(
                "//app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]/div/div/img[2]"));

        WebElement belCard = driver.findElement(By.xpath(
                "//app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]/div/div/img[3]"));

        WebElement mir = driver.findElement(By.xpath(
                "//app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]/div/div/div/img[1]"));

        Assert.assertTrue(visa.isDisplayed());

        Assert.assertTrue(masterCard.isDisplayed());

        Assert.assertTrue(belCard.isDisplayed());

        Assert.assertTrue(mir.isDisplayed());

        driver.quit();
    }
}
