package by.epam.learn.webdriver.hurtmeplenty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EstimatePage {
    WebDriver driver;

    public EstimatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(),'VM class:')]")
    public WebElement machineClassField;

    @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(),'Instance type:')]")
    WebElement instanceTypeField;

    @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(),'Region:')]")
    WebElement regionField;

    @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(),'Total available local SSD space')]")
    WebElement ssdField;

    @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(),'Commitment term:')]")
    WebElement commitmentTermField;

    @FindBy(xpath = "//div[@class='md-list-item-text']")
    WebElement totalCostField;


    public String findEstimateMachineClassText() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(machineClassField));
        return machineClassField.getText();
    }

    public String findEstimateInstanceTypeText() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(instanceTypeField));
        return instanceTypeField.getText();
    }

    public String findEstimateRegionText() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(regionField));
        return regionField.getText();
    }

    public String findEstimateSsdText() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(ssdField));
        return ssdField.getText();
    }

    public String findCommitmentTermText() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(commitmentTermField));
        return commitmentTermField.getText();
    }

    public String findTotalCost() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(totalCostField));
        return totalCostField.getText();
    }
}
