package by.epam.learn.webdriver.hardcore;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage {
    private WebDriver driver;
    private String totalCostOnPage;
    private static final String NUMBER_OF_INSTANCE = "4";

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTotalCostOnPage() {
        return totalCostOnPage;
    }

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    WebElement firstFrame;
    @FindBy (id = "myFrame")
    WebElement secondFrame;
    @FindBy (xpath = "//*[@id='mainForm']//div[@title='Compute Engine']")
    WebElement computeEngineButton;

    @FindBy (id = "input_60")
    WebElement numberOfInstancesField;

    @FindBy (xpath = "(//md-checkbox[@aria-label='Add GPUs' and @role='checkbox'])[1]")
    WebElement addGpuCheckbox;
    @FindBy (xpath = "//form[@name='ComputeEngineForm']//button[@aria-label = 'Add to Estimate']")
    WebElement buttonAddToEstimate;

    @FindBy (xpath = "//*[@id='resultBlock']//h2[@class='md-title']")
    WebElement totalEstimatedCost;

    public CalculatorPage clickComputerEngineButton() {
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);
        waitVisibilityOf(computeEngineButton);
        clickThroughJS(computeEngineButton);
        return this;
    }

    public CalculatorPage fillNumberOfInstances() {
        numberOfInstancesField.sendKeys(NUMBER_OF_INSTANCE);
        return this;
    }

    public EstimatePage fillRequiredData() {
        specifyOptionFromDropdownList("Operating System / Software", "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS");
        specifyOptionFromDropdownList("Machine Class", "Regular");
        specifyOptionFromDropdownList("Machine type", "n1-standard-8 (vCPUs: 8, RAM: 30GB)");
        clickCheckbox(addGpuCheckbox);
        specifyOptionFromDropdownList("Number of GPUs", "1");
        specifyOptionFromDropdownList("GPU type", "NVIDIA Tesla V100");
        specifyOptionFromDropdownList("Local SSD", "2x375 GB");
        specifyOptionFromDropdownList("Datacenter location", "Frankfurt (europe-west3)");
        specifyOptionFromDropdownList("Committed usage", "1 Year");
        clickButton(buttonAddToEstimate);
        waitVisibilityOf(totalEstimatedCost);
        totalCostOnPage = totalEstimatedCost.getText();
        return new EstimatePage(driver);
    }

    public CalculatorPage clickCheckbox(WebElement checkbox) {
        waitVisibilityOf(checkbox);
        clickThroughJS(checkbox);
        return this;
    }

    public CalculatorPage clickButton(WebElement button) {
        waitVisibilityOf(button);
        clickThroughJS(button);
        return this;
    }

    public void waitVisibilityOf(WebElement element){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
    }

    public void clickThroughJS(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void specifyOptionFromDropdownList(String dropdownList, String option) {
        String dropdownXpath = String.format("//label[text()='%s']/..//md-select", dropdownList);
        WebElement specifiedDropdown = driver.findElement(By.xpath(dropdownXpath));
        waitVisibilityOf(specifiedDropdown);
        clickThroughJS(specifiedDropdown);

        String ariaOwns = specifiedDropdown.getAttribute("aria-owns");

        String xpath = String.format("//*[id='%s']//div[contains(text(), '%s')]", ariaOwns, option);
        WebElement specifiedOption = driver.findElement(By.xpath(xpath));
        waitVisibilityOf(specifiedOption);
        clickThroughJS(specifiedOption);
    }
}
