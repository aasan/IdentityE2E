# Identity E2E testing exercise
Feature: Verify Data provided in input file exists on the https://cartaxcheck.co.uk/ website and compare data from the web page to output File

  @RegistrationCheck
  Scenario: Verify Data from input file website and compare to out file
    Given I get all cars data from the input test file "car_input.txt,input.txt"
    And   I read data from file and get all car registration match format
    Then  I should get list of all car registration
    And   I navigate to cartaxacheck website
    Then  I should see "Free Car Check" heading
    And   I enter All Car Registration, click Free Car Check button, get Car information
    And   I compare each Car Registration information to data stored in output file "car_output.txt"
