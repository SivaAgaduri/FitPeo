package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Homepage;
import pages.RevenueCalculator;

import java.time.Duration;

public class FitPeoStepDefinition {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Homepage homepage;
    private RevenueCalculator revenueCalculator;
    public FitPeoStepDefinition(){
        driver = Hooks.driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }
    @Given("Navigate to the Fitpeo homepage")
    public void navigate_to_the_fitpeo_homepage() {
        homepage = new Homepage(driver,webDriverWait);
        homepage.navigateToHomepage();
        homepage.validateHomePage();
    }
    @Then("Click on the Revenue Calculator button")
    public void click_on_the_revenue_calculator_button() {
        homepage.clickOnRevenueCalculatorMenu();
        revenueCalculator = new RevenueCalculator(driver,webDriverWait);
    }
    @Then("Verify whether Revenue Calculator page is displayed or not")
    public void verify_whether_revenue_calculator_page_is_displayed_or_not() {
        // Write code here that turns the phrase above into concrete actions
        revenueCalculator.validatePageUrl();
    }
    @Then("Scroll to the slider and update the slider value to {string}")
    public void update_the_slider_value_to(String sliderValue) {
        // Write code here that turns the phrase above into concrete actions
        revenueCalculator.updateTheSlider(sliderValue);
    }
    @Then("Validate whether the {string} is updated in the text box")
    public void validate_whether_the_is_updated_in_the_text_box(String expectedValue) {
        // Write code here that turns the phrase above into concrete actions
        String actualAmountValue = revenueCalculator.getAmountTextBoxValue();
        Assert.assertEquals("Validating the amount in text box",expectedValue,actualAmountValue);
    }
    @Then("Update the value in text box with {string}")
    public void update_the_value_in_text_box_with(String amountTextBoxValue) {
        // Write code here that turns the phrase above into concrete actions
        revenueCalculator.setValueInAmountTextBox(amountTextBoxValue);

    }
    @Then("Validate whether the slider is updated or not with {string}")
    public void validate_whether_the_slider_is_updated_or_not_with(String expectedValue) {
        // Write code here that turns the phrase above into concrete actions
        String actualSliderValue = revenueCalculator.getSliderValue();
        Assert.assertEquals("Validating slider value",expectedValue,actualSliderValue);
    }
    @Then("Scroll to the check boxes and select the checkboxes of {string}, {string}, {string} and {string}")
    public void scroll_to_the_check_boxes_and_select_the_checkboxes_of_and(String checkBox1,String checkBox2,String checkBox3,String checkBox4) {
        // Write code here that turns the phrase above into concrete actions
        String[] checkBoxes={checkBox1,checkBox2,checkBox3,checkBox4};
        revenueCalculator.selectTheCheckBoxes(checkBoxes);
    }
    @Then("Validate whether the Total Recurring Reimbursement amount for all patients to {string}")
    public void validate_whether_the_total_recurring_reimbursement_amount_for_all_patients_to(String totalReimbursementAmount) {
        // Write code here that turns the phrase above into concrete actions
        revenueCalculator.validateTotalReimbursementAmount(totalReimbursementAmount);
    }

}
