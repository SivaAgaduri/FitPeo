package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseDriverClass;

import java.time.Duration;

public class Homepage extends BaseDriverClass {

    private String url="https://www.fitpeo.com/";
    private String title="Remote Patient Monitoring (RPM) - fitpeo.com";
    private WebDriver driver;
    @FindBy(xpath = "//header//a[@href='/revenue-calculator']")
    private WebElement revenueCalculatorButton;

    public Homepage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    /**
     * This method navigates to the homepage
     */
    public void navigateToHomepage(){
        this.driver.get(url);
    }

    /**
     * This method validates the homepage url and title
     */
    public void validateHomePage(){
        Assert.assertEquals("Validating URL",this.url,this.driver.getCurrentUrl());
        Assert.assertEquals("Validating title",this.title,this.driver.getTitle());
    }

    /**
     * This method clicks on the Revenue calculator menu
     */
    public void clickOnRevenueCalculatorMenu(){
        waitUntilElementVisible(revenueCalculatorButton);
        revenueCalculatorButton.click();
        waitForTimeOut(Duration.ofSeconds(5));
    }
}
