package by.epam.learn.webdriver.hurtmeplenty;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage {
    WebDriver driver;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String NUMBER_OF_INSTANCE = "4";

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    WebElement firstFrame;
    @FindBy (id = "myFrame")
    WebElement secondFrame;
    @FindBy (xpath = "//*[@id='mainForm']//div[@title='Compute Engine']")
    WebElement computeEngineButton;

    @FindBy (id = "input_60")
    WebElement numberOfInstancesField;

    @FindBy (id = "select_value_label_53")
    WebElement operationSystemDropdown;
    @FindBy (id = "select_option_62")
    WebElement operationSystemChoice;

    @FindBy (id = "select_value_label_54")
    WebElement machineClassDropdown;
    @FindBy (id = "select_option_74")
    WebElement machineClassChoice;

    @FindBy (xpath = "//md-select-value[@id='select_value_label_57']/span[1]/div")
    WebElement machineTypeDropdown;
    @FindBy (xpath = "//md-option[@id='select_option_227']/div[contains(text(), 'n1-standard-8')]")
    WebElement machineTypeChoice;

    @FindBy (xpath = "(//md-checkbox[@aria-label='Add GPUs' and @role='checkbox'])[1]")
    WebElement addGpuCheckbox;
    @FindBy (id = "select_value_label_349")
    WebElement numberOfGPUsDropdown;
    @FindBy (id = "select_option_356")
    WebElement numberOfGPUsChoice;

    @FindBy (id = "select_value_label_350")
    WebElement typeOfGPUDropdown;
    @FindBy (id = "select_option_363")
    WebElement typeOfGPUChoice;

    @FindBy (id = "select_value_label_183")
    WebElement localSSDDropdown;
    @FindBy (id = "select_option_248")
    WebElement localSSDChoice;

    @FindBy (id = "select_value_label_58")
    WebElement locationDropdown;
    @FindBy (id = "select_option_195")
    WebElement locationChoice;

    @FindBy (id = "select_value_label_59")
    WebElement committedUsageDropdown;
    @FindBy (id = "select_option_92")
    WebElement committedUsageChoice;

    @FindBy (xpath = "//form[@name='ComputeEngineForm']//button[@aria-label = 'Add to Estimate']")
    WebElement buttonAddToEstimate;

    public CalculatorPage clickComputerEngineButton() {
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);
        clickButton(computeEngineButton);
        return this;
    }

    public CalculatorPage fillNumberOfInstances() {
        numberOfInstancesField.sendKeys(NUMBER_OF_INSTANCE);
        return this;
    }

    public EstimatePage fillRequiredData() {
        selectDropdownAndSelectChoice(operationSystemDropdown, operationSystemChoice);
        selectDropdownAndSelectChoice(machineClassDropdown, machineClassChoice);
        selectDropdownAndSelectChoice(machineTypeDropdown, machineTypeChoice);
        clickCheckbox(addGpuCheckbox);
        selectDropdownAndSelectChoice(numberOfGPUsDropdown, numberOfGPUsChoice);
        selectDropdownAndSelectChoice(typeOfGPUDropdown, typeOfGPUChoice);
        selectDropdownAndSelectChoice(localSSDDropdown, localSSDChoice);
        selectDropdownAndSelectChoice(locationDropdown, locationChoice);
        selectDropdownAndSelectChoice(committedUsageDropdown, committedUsageChoice);
        clickButton(buttonAddToEstimate);
        return new EstimatePage(driver);
    }

    public CalculatorPage selectDropdownAndSelectChoice(WebElement choice, WebElement desired) {
        waitVisibilityOf(choice);
        clickThroughJS(choice);
        waitVisibilityOf(desired);
        clickThroughJS(desired);
        return this;
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
}
