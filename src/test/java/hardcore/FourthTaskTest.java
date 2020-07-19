package hardcore;

import by.epam.learn.webdriver.hardcore.CalculatorPage;
import by.epam.learn.webdriver.hardcore.GoogleCloudPage;
import by.epam.learn.webdriver.hardcore.TemporaryEmailPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FourthTaskTest {
    private WebDriver driver;
    private TemporaryEmailPage resultPage;

    @BeforeMethod(alwaysRun = true)
    public void getSourceData() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        resultPage = new GoogleCloudPage(driver)
                .openHomePage()
                .fillInSearchInputLine()
                .selectDesiredSearchResult()
                .clickComputerEngineButton()
                .fillInNumberOfInstance()
                .fillInRequiredData()
                .clickEmailEstimateButton()
                .getEmail()
                .addEmail();
    }

    @Test
    public void compareCostsFromDifferentSources() {
        Assert.assertTrue(CalculatorPage.costOnPage.contains(resultPage.getCostInLetter()));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
//        driver.quit();
        driver = null;
    }
}
