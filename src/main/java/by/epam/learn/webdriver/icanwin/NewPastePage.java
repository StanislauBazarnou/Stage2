package by.epam.learn.webdriver.icanwin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewPastePage {
    private WebDriver driver;

    public NewPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='paste_code']")
    private WebElement pasteForm;
    @FindBy(xpath = "//span[contains(text(), 'Never')]")
    private WebElement pasteExpirationDropdown;
    @FindBy(xpath = "//ul[@class='select2-results__options']/li[text()='10 Minutes']")
    private WebElement expirationDropdownChoose;
    @FindBy(xpath = "//div[@class='form_right']/input[@name='paste_name']")
    private WebElement pasteNameField;
    @FindBy(xpath = "//input[@id='submit']")
    private WebElement createPasteBtn;

    public NewPastePage fillNewPasteField(String textForPasteForm) {
        pasteForm.sendKeys(textForPasteForm);
        return this;
    }

    public NewPastePage pasteExpirationFieldSelectDropdown() {
        pasteExpirationDropdown.click();
        expirationDropdownChoose.click();
        return this;
    }

    public NewPastePage fillPasteNameField(String textForNameField) {
        pasteNameField.click();
        pasteNameField.sendKeys(textForNameField);
        return this;
    }

    public CreatedPastePage clickCreatePasteBtn() {
        createPasteBtn.click();
        return new CreatedPastePage(driver);
    }
}
