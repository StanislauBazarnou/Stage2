package by.epam.learn.webdriver.hardcore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class TemporaryEmailPage {
    WebDriver driver;

    public static final String TEMPORARY_MAIL_URL = "https://10minutemail.com";
    public static final By MAIL_LOCATOR = By.xpath("//*[@id='mail_address']");
    public static final By EXPECTED_MAIL_LOCATOR = By.xpath("//*[@id='mail_messages_content']//div[@class = 'small_message_icon_box']");
    public static final By COST_FROM_MAIL_LOCATOR = By.xpath("//*[@id='mobilepadding']/td/h2");

    public TemporaryEmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public EstimatePage getEmail() {
        driver.get(TEMPORARY_MAIL_URL);
        WebElement mail = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(MAIL_LOCATOR));
        new WebDriverWait(driver, 20).until(ExpectedConditions.attributeToBeNotEmpty(mail, "value"));
        String emailStringValue = mail.getAttribute("value");
        ArrayList<String> browserPages = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserPages.get(0));
        return new EstimatePage(driver, emailStringValue);
    }

    public String getCostInLetter() {
        WebElement expectedMail = new WebDriverWait(driver, 180)
                .until(ExpectedConditions.elementToBeClickable(EXPECTED_MAIL_LOCATOR));
        expectedMail.click();
        WebElement costFromMail = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(COST_FROM_MAIL_LOCATOR));
        String costInLetter = costFromMail.getText();
        String[] array = costInLetter.split(":");
        return  array[1].trim();
    }
}
