package by.epam.learn.webdriver.hardcore;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class EstimatePage {
    private WebDriver driver;
    private String email;

    public EstimatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public EstimatePage(WebDriver driver, String email) {
        this.driver = driver;
        this.email = email;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='email_quote']")
    WebElement emailEstimateButton;

    @FindBy (xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    WebElement firstFrameEmail;

    @FindBy (xpath = "//*[@id='myFrame']")
    WebElement secondFrameEmail;

    @FindBy (xpath = "//input[@ng-model='emailQuote.user.email']")
    WebElement emailField;

    @FindBy (xpath = "//button[@aria-label='Send Email']")
    WebElement emailSendButton;

    public TemporaryEmailPage clickEmailEstimateButton() {
        waitVisibilityOf(emailEstimateButton);
        clickThroughJS(emailEstimateButton);
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> browserPages = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserPages.get(1));
        return new TemporaryEmailPage(driver);
    }

    public TemporaryEmailPage addEmail() {
        driver.switchTo().frame(firstFrameEmail).switchTo().frame(secondFrameEmail); //for what?
        waitVisibilityOf(emailField);
        emailField.sendKeys(email);
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(emailSendButton));
        clickThroughJS(emailSendButton);
        ArrayList<String> browserPages = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserPages.get(1));
        return new TemporaryEmailPage(driver);
    }

    public void waitVisibilityOf(WebElement element){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
    }

    public void clickThroughJS(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
