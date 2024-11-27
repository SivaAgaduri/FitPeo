package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseDriverClass;

import java.time.Duration;

public class RevenueCalculator extends BaseDriverClass {
    private String url="https://www.fitpeo.com/revenue-calculator";
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//h4[text()='Medicare Eligible Patients']")
    private WebElement heading;
    @FindBy(xpath = "//input[@type='range']")
    private WebElement amountSlider;
    @FindBy(xpath = "//input[@type='number']")
    private WebElement amountSliderTextBox;

    @FindBy(xpath = "//p[text()='Total Recurring Reimbursement for all Patients Per Month:']/p")
    private WebElement totalReimbursementAmount;
    public RevenueCalculator(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver=driver;
        this.wait = wait;
        PageFactory.initElements(driver,this);
    }

    /**
     * This method validates the revenue calculator page url
     */
    public void validatePageUrl(){
        waitUntilElementVisible(heading);
        Assert.assertEquals("Validating revenue calculator page url",this.url,this.driver.getCurrentUrl());
    }

    /**
     * This method updates the slider value by mouse actions
     * @param value Indicates the slider value
     */
    public void updateTheSlider(String value){
        scrollToTheElement(amountSlider);
        // Get slider properties using JavaScriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int minValue = Integer.parseInt((String) js.executeScript("return arguments[0].min", amountSlider));
        int maxValue = Integer.parseInt((String) js.executeScript("return arguments[0].max", amountSlider));
        int sliderWidth = amountSliderTextBox.getSize().width;
        // Calculate the percentage and x-offset
        double percentage = (double) (Integer.parseInt(value) - minValue) / (maxValue - minValue);
        int xOffset = (int) (percentage * sliderWidth);
        // Create an Actions class instance
        Actions actions = new Actions(driver);
        // Perform the drag and drop action
        actions.dragAndDropBy(amountSlider, -30, 0).build().perform();
        actions.dragAndDropBy(amountSlider, xOffset, 0).build().perform();
        waitForTimeOut(Duration.ofSeconds(5));
    }

    /**
     * This method returns the current slider value
     * @return Value of the slider
     */
    public String getSliderValue(){
        scrollToTheElement(amountSlider);
        waitForTimeOut(Duration.ofSeconds(2));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value;", amountSlider);
    }

    /**
     * This method helps to update the amount value in text box
     * @param value Value of the amount to be updated
     */
    public void setValueInAmountTextBox(String value) {
        Actions actions = new Actions(driver);
        actions.moveToElement(amountSliderTextBox).click()
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        amountSliderTextBox.sendKeys(value);
        wait.until(ExpectedConditions.attributeToBe(amountSliderTextBox,"value",value));
        waitForTimeOut(Duration.ofSeconds(5));
    }

    /**
     * This method returns the existing value in the text box
     * @return Text box value
     */
    public String getAmountTextBoxValue(){
        return amountSliderTextBox.getAttribute("value");
    }

    /**
     * This method checks the checkboxes based on check box id
     * @param checkboxes Checkboxes Id's in array of strings
     */
    public void selectTheCheckBoxes(String[] checkboxes){
        for (String checkBox:checkboxes
             ) {
            try{
                WebElement element = this.driver.findElement(By.xpath("//p[text()='"+checkBox+"']/following-sibling::label/span/input[@type='checkbox']"));
                // Interact with the checkbox (e.g., select it)
                if (!element.isSelected()) {
                    element.click();
                }
                waitForTimeOut(Duration.ofSeconds(2));
            }catch (NoSuchElementException e){
                Assert.fail("No such checkbox to select:"+checkBox);
            }

        }
    }

    /**
     * This method validates the total amount
     * @param totalAmount Expected total amount
     */
    public void validateTotalReimbursementAmount(String totalAmount){
        Assert.assertEquals("Validating total amount",totalReimbursementAmount.getText(),"$"+totalAmount);
    }

}
