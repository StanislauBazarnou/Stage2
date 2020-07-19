package by.epam.learn.webdriver.icanwin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreatedPastePage {
    private WebDriver driver;

    public CreatedPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}
