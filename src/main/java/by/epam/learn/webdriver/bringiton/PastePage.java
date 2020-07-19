package by.epam.learn.webdriver.bringiton;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastePage {
    private WebDriver driver;

    public PastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='paste_box_line1' and @title]")
    private WebElement pageTitle;
    @FindBy(xpath = "//div[@id='code_buttons']//a[contains(., 'Bash')]")
    private WebElement syntaxForBash;
    @FindBy(xpath = "//textarea[@id='paste_code']")
    private WebElement textAreaText;
    @FindBy(xpath = "//textarea[@name='paste_code']")
    private WebElement textArea;
    @FindBy(xpath = "//div[@class='form_right']/input[@name='paste_name']")
    private WebElement nameField;
    @FindBy(xpath = "//input[@id='submit']")
    private WebElement createPasteBtn;

    public PastePage fillTextInTextArea(String inputText) {
        textArea.sendKeys(inputText);
        return this;
    }

    public PastePage selectOption(String listName, String listOption) {
        String dropdownXpath = String.format("//div[@class='form_frame_left']//span[contains(text(), '%s')]", listName);
        String optionXpath = String.format("//span[@class='select2-results']//li[contains(., '%s')]", listOption);
        driver.findElement(By.xpath(dropdownXpath)).click();
        driver.findElement(By.xpath(optionXpath)).click();
        return this;
    }

    public PastePage pasteNameFieldSelectDropdown(String text) {
        nameField.click();
        nameField.sendKeys(text);
        return this;
    }

    public void pressCreateNewPasteBtn() {
        createPasteBtn.click();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getSyntaxForBash() {
        return syntaxForBash;
    }

    public WebElement getTextAreaText() {
        return textAreaText;
    }

    public WebElement getTextArea() {
        return textArea;
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getCreatePasteBtn() {
        return createPasteBtn;
    }
}
