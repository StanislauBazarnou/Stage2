package by.epam.learn.webdriver.hurtmeplenty;

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
    @FindBy (xpath = "(//div[contains(text(),'Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS')])[1]")
    WebElement operationSystemChoice;

    @FindBy (xpath = "//label[text()='Machine Class']//parent::md-input-container//div[@class='md-text']")
    WebElement choiceOfMachineClass;
    @FindBy (xpath = "//md-option[@id='select_option_74']/div[@class='md-text']")
    WebElement machineClassChoice;

    @FindBy (xpath = "//md-select-value[@id='select_value_label_57']/span[1]/div")
    WebElement machineTypeDropdown;
    @FindBy (xpath = "//md-option[@id='select_option_227']/div[contains(text(), 'n1-standard-8')]")
    WebElement machineTypeChoice;

    @FindBy (xpath = "//md-card-content[@id='mainForm']//div[contains(text(), 'GPUs aren’t available for')]/..//md-checkbox")
    WebElement addGpuCheckbox;
    @FindBy (xpath = "//md-select-value[@id='select_value_label_349']/span/div")
    WebElement numberOfGPUsDropdown;
    @FindBy (xpath = "//md-option[@id='select_option_401']/div")
    WebElement numberOfGPUsChoice;

    @FindBy (xpath = "//md-select-value[@id='select_value_label_350']//div")
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
        selectDropdownAndSelectChoice(choiceOfMachineClass, machineClassChoice);
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
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(choice));
        choice.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(desired));
        desired.click();
        return this;
    }

    public CalculatorPage clickCheckbox(WebElement checkbox) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(checkbox));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        checkbox.click();
        return this;
    }

    public CalculatorPage clickButton(WebElement button) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(button));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        button.click();
        return this;
    }
}
