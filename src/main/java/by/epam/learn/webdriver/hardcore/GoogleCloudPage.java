package by.epam.learn.webdriver.hardcore;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPage {
    private WebDriver driver;

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private static final String REQUEST_TEXT = "Google Cloud Platform Pricing Calculator";


    @FindBy(xpath = "//div[@class='devsite-searchbox']")
    WebElement searchButton;

    @FindBy(name = "q")
    WebElement searchInputLine;

    public GoogleCloudPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudPage openHomePage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(searchButton));
        return this;
    }

    public SearchResultsPage fillInSearchInputLine() {
        searchButton.click();
        searchInputLine.sendKeys(REQUEST_TEXT + Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}
