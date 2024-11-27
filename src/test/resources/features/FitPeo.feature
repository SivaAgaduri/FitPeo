Feature: Validation of Fitpeo website
  Scenario Outline: Validation of Revenue Calculator page
    Given Navigate to the Fitpeo homepage
    Then Click on the Revenue Calculator button
    And Verify whether Revenue Calculator page is displayed or not
    And Scroll to the slider and update the slider value to "<sliderValue1>"
    Then Validate whether the "<sliderValue1>" is updated in the text box
    And Update the value in text box with "<sliderValue2>"
    Then Validate whether the slider is updated or not with "<sliderValue2>"
    And Scroll to the check boxes and select the checkboxes of "<checkbox1>", "<checkbox2>", "<checkbox3>" and "<checkbox4>"
    Then Validate whether the Total Recurring Reimbursement amount for all patients to "<reimbursementAmount>"
    Examples:
      |sliderValue1|sliderValue2|checkbox1|checkbox2|checkbox3|checkbox4|reimbursementAmount|
      |560         |820         |CPT-99091|CPT-99453|CPT-99454|CPT-99474|110700             |
