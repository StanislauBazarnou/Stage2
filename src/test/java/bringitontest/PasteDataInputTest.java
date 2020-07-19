package bringitontest;

import by.epam.learn.webdriver.bringiton.PastePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PasteDataInputTest {
    private static final String INPUT_TEXT = "git config --global user.name  'New Sheriff in Town'\n" +
            "git reset $(git commit-tree HEAD^{tree} -m 'Legacy code')\n" +
            "git push origin master --force";

    private WebDriver driver;
    private PastePage page;

    @BeforeClass(alwaysRun = true)
    public void browserSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://pastebin.com");

        page = new PastePage(driver);

        page.fillTextInTextArea(INPUT_TEXT)
                .selectOption("None", "Bash")
                .selectOption("Never", "10 Minutes")
                .pasteNameFieldSelectDropdown("how to gain dominance among developers")
                .pressCreateNewPasteBtn();

        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        driver.get(driver.getCurrentUrl());
    }

    @Test(description = "checking pastebian page title is correct")
    public void pageTitleIsCorrect() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "[Bash] how to gain dominance among developers - Pastebin.com";
        assertEquals(actualTitle, expectedTitle);
    }

    @Test(description = "checking that syntax highlighting for bash")
    public void syntaxIsForBash() {
        assertTrue(page.getSyntaxForBash().isDisplayed());
    }

    @Test(description = "checking text area has correct text")
    public void textInTextAreaIsCorrect() {
        String actualText = "git config --global user.name  'New Sheriff in Town'\n" +
                "git reset $(git commit-tree HEAD^{tree} -m 'Legacy code')\n" +
                "git push origin master --force";
        assertEquals(page.getTextAreaText().getText(), actualText);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
