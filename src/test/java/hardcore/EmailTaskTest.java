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

public class EmailTaskTest {
    private WebDriver driver;
    private TemporaryEmailPage resultPage;
    private CalculatorPage calculatorPage;
    private String result;

    public String getResult() {
        return result;
    }

    @BeforeMethod(alwaysRun = true)
    public void getSourceData() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        result = new GoogleCloudPage(driver)
                .openHomePage()
                .fillInSearchInputLine()
                .selectDesiredSearchResult()
                .clickComputerEngineButton()
                .fillNumberOfInstances()
                .fillRequiredData()
                .clickEmailEstimateButton()
                .getEmail()
                .addEmail()
                .getCostInLetter();
    }

    @Test
    public void compareCostsFromDifferentSources() {
        Assert.assertTrue(result.contains(getResult()));
        System.out.println(result + " = " + getResult());
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
//        driver.quit();
        driver = null;
    }
}
