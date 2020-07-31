package icanwintest;

import by.epam.learn.webdriver.icanwin.NewPastePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewPastePageTest {
    private WebDriver driver;
    private static final String EXPECTED_TITLE = "helloweb - Pastebin.com";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void createPasteTest() {
        driver.get("https://pastebin.com");
        String actualTitle = new NewPastePage(driver)
                .fillNewPasteField("Hello from WebDriver")
                .pasteExpirationFieldSelectDropdown()
                .fillPasteNameField("helloweb")
                .clickCreatePasteBtn()
                .getPageTitle();

        Assert.assertEquals(actualTitle, EXPECTED_TITLE, "New paste wasn't created");
    }

    @AfterMethod(alwaysRun = true)
    public void browserShutDown() {
//        driver.quit();
        driver = null;
    }
}
