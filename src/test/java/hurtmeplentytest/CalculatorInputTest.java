package hurtmeplentytest;

import by.epam.learn.webdriver.hurtmeplenty.EstimatePage;
import by.epam.learn.webdriver.hurtmeplenty.GoogleCloudPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculatorInputTest {
    private WebDriver driver;
    private EstimatePage resultPage;

    private static final String EXPECTED_MACHINE_CLASS = "regular";
    private static final String EXPECTED_INSTANCE_TYPE = "n1-standard-8";
    private static final String EXPECTED_REGION = "Frankfurt";
    private static final String EXPECTED_SSD = "2x375 GiB";
    private static final String EXPECTED_COMMITMENT_TERM = "1 Year";
    private static final String EXPECTED_TOTAL_COST = "1,082.77";

    @BeforeClass(alwaysRun = true)
    public void makeAnEstimateOfComputeEngineRent() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        resultPage = new GoogleCloudPage(driver)
                .openHomePage()
                .fillInSearchInputLine()
                .selectDesiredSearchResult()
                .clickComputerEngineButton()
                .fillNumberOfInstances()
                .fillRequiredData();
    }

    @Test
    public void machineClassFieldMatching() {
        Assert.assertTrue(resultPage.findEstimateMachineClassText().contains(EXPECTED_MACHINE_CLASS));
    }

    @Test
    public void instanceTypeFieldMatching() {
        Assert.assertTrue(resultPage.findEstimateInstanceTypeText().contains(EXPECTED_INSTANCE_TYPE));
    }

    @Test
    public void regionFieldMatching() {
        Assert.assertTrue(resultPage.findEstimateRegionText().contains(EXPECTED_REGION));
    }

    @Test
    public void ssdFieldMatching() {
        Assert.assertTrue(resultPage.findEstimateSsdText().contains(EXPECTED_SSD));
    }

    @Test
    public void commitmentTermFieldMatching() {
        Assert.assertTrue(resultPage.findCommitmentTermText().contains(EXPECTED_COMMITMENT_TERM));
    }

    @Test
    public void totalCostFieldMatching() {
        Assert.assertTrue(resultPage.findTotalCost().contains(EXPECTED_TOTAL_COST));
    }

    @AfterClass(alwaysRun = true)
    public void browserTearDown() {
//        driver.quit();
        driver = null;
    }
}
