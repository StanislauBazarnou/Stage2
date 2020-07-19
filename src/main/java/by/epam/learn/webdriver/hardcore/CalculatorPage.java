package by.epam.learn.webdriver.hardcore;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage {
    WebDriver driver;
    public static String costOnPage;

    private static final String NUMBER_OF_INSTANCE = "4";

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    WebElement firstFrame;

    @FindBy (id = "myFrame")
    WebElement secondFrame;

    @FindBy (xpath = "//*[@id='mainForm']//div[@title='Compute Engine']")
    WebElement computeEngineButton;

    @FindBy (id = "input_55")
    WebElement inputLineNumberOfInstances;

    @FindBy(id = "select_value_label_48")
    WebElement choiceOfOperationSystem;

    @FindBy (id = "select_option_57")
    WebElement desiredOperationSystem;

    @FindBy (id = "select_value_label_49")
    WebElement choiceOfMachineClass;

    @FindBy (id = "select_option_69")
    WebElement desiredMachineClass;

    @FindBy (id = "select_value_label_52")
    WebElement choiceOfInstanceType;

    @FindBy (id = "select_option_208")
    WebElement desiredInstanceType;

    @FindBy (xpath = "//md-checkbox[@aria-label='Add GPUs']")
    WebElement addGPUChexkbox;

    @FindBy (id = "select_value_label_326")
    WebElement choiceOfNumberOfGPUs;

    @FindBy (id = "select_option_333")
    WebElement desiredNumberOfGPUs;

    @FindBy (id = "select_value_label_327")
    WebElement choiceOfGPUType;

    @FindBy (id = "select_option_340")
    WebElement desiredGPUType;

    @FindBy (id = "select_value_label_166")
    WebElement choiceOfLocalSSD;

    @FindBy (id = "select_option_229")
    WebElement desiredLocalSSD;

    @FindBy (id = "select_value_label_53")
    WebElement choiceOfLocation;

    @FindBy (id = "select_option_177")
    WebElement desiredLocation;

    @FindBy (id = "select_value_label_54")
    WebElement choiceOfCommittedUsage;

    @FindBy (id = "select_option_87")
    WebElement desiredCommittedUsage;

    @FindBy (xpath = "//button[@aria-label='Add to Estimate']")
    WebElement buttonAddToEstimate;

    @FindBy (xpath = "//*[@id='resultBlock']//h2[@class='md-title']")
    WebElement costOnPageDOMElement;

    public CalculatorPage clickComputerEngineButton() {
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(computeEngineButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", computeEngineButton);
        return this;
    }

    public CalculatorPage fillInNumberOfInstance() {
        inputLineNumberOfInstances.sendKeys(NUMBER_OF_INSTANCE);
        return this;
    }

    public EstimatePage fillInRequiredData() {
        expandSelectionOptionsAndSelectDesired(choiceOfOperationSystem, desiredOperationSystem);
        expandSelectionOptionsAndSelectDesired(choiceOfMachineClass, desiredMachineClass);
        expandSelectionOptionsAndSelectDesired(choiceOfInstanceType, desiredInstanceType);
        clickCheckbox(addGPUChexkbox);
        expandSelectionOptionsAndSelectDesired(choiceOfNumberOfGPUs, desiredNumberOfGPUs);
        expandSelectionOptionsAndSelectDesired(choiceOfGPUType, desiredGPUType);
        expandSelectionOptionsAndSelectDesired(choiceOfLocalSSD, desiredLocalSSD);
        expandSelectionOptionsAndSelectDesired(choiceOfLocation, desiredLocation);
        expandSelectionOptionsAndSelectDesired(choiceOfCommittedUsage, desiredCommittedUsage);
        clickButton(buttonAddToEstimate);
        costOnPage = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOf(costOnPageDOMElement)).getText();
        return new EstimatePage(driver);
    }

    public CalculatorPage expandSelectionOptionsAndSelectDesired(WebElement choice, WebElement desired) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(choice));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", choice);
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(desired));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", desired);
        return this;
    }

    public CalculatorPage clickCheckbox(WebElement checkbox) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(checkbox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        return this;
    }

    public CalculatorPage clickButton(WebElement button) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(button));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        return this;
    }
}
