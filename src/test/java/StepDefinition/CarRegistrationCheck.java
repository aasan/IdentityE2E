package StepDefinition;

import Common.Helper;
import PageObjectModel.CarRegistrationPageObj;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CarRegistrationCheck {
    CarRegistrationPageObj carRegistrationPageObj;
    private WebDriver driver;
    private String carData;
    private String carRegPattern= "[a-zA-Z]{2}+[0-9]{2}+[a-zA-Z]{3}|[a-zA-Z]{2}+[0-9]{2}+\\s[a-zA-Z]{3}";
    List<String> carRegList;
    List<String> carRegistration;
    List<String> carMake;
    List<String> carYear;
    List<String> carModel;
    List<String> carColour;
    public CarRegistrationCheck( ) {
        driver=Helper.initiateChromeBrowser();
        carRegistrationPageObj = new CarRegistrationPageObj(driver);
        carRegistration = new ArrayList<String>();
        carColour = new ArrayList<String>();
        carMake = new ArrayList<String>();
        carModel = new ArrayList<String>();
        carYear = new ArrayList<String>();
    }

    @Given("^I get all cars data from the input test file \"([^\"]*)\"$")
    public void i_get_all_cars_data_from_the_input_test_file(String fileName)  {
        carData = carRegistrationPageObj.readFilesData(fileName);
    }

    @Given("^I read data from file and get all car registration match format$")
    public void i_read_data_from_file_and_get_all_car_registration_match_format()  {
        carRegList= carRegistrationPageObj.getAllCarRegistration(carData,carRegPattern);
    }

    @Then("^I should get list of all car registration$")
    public void i_should_get_list_of_all_car_registration()  {
        Assert.assertTrue(carRegList.size()>0,"Data from file do not contain any matching Car Registration data");
    }

    @Then("^I navigate to cartaxacheck website$")
    public void i_navigate_to_cartaxacheck_website() throws Throwable {
        driver.navigate().to("https://cartaxcheck.co.uk/");

    }
    
    @Then("^I should see \"([^\"]*)\" heading$")
    public void i_should_see_heading(String heading) throws Throwable {
        Assert.assertTrue(carRegistrationPageObj.freeCarCheckHeading.getText().equals(heading));
    }

    @Then("^I enter All Car Registration, click Free Car Check button, get Car information$")
    public void i_enter_All_Car_Registration_click_Free_Car_Check_button_get_Car_information() throws Throwable {

        for(int i=0;i<carRegList.size();i++) {
            carRegistrationPageObj.carRegistrationEntry(carRegList.get(i));
            List <WebElement> vehicleNotFound= null;
            vehicleNotFound = driver.findElements(By.linkText("Try Again"));
            if(vehicleNotFound.size()<1) {
                carRegistration.add(carRegistrationPageObj.carRegistration.getText());
                carMake.add(carRegistrationPageObj.carMake.getText());
                carModel.add(carRegistrationPageObj.carModel.getText());
                carColour.add(carRegistrationPageObj.carColour.getText());
                carYear.add(carRegistrationPageObj.carYear.getText());
            }
            else {
            System.out.println(carRegList.get(i) +
                    " Vehicle registration is in valid and not found");
                carRegistration.add(carRegList.get(i));
                carMake.add("No Data Found");
                carModel.add("No Data Found");
                carColour.add("No Data Found");
                carYear.add("No Data Found");
            vehicleNotFound.get(0).click();
                     }
            carRegistrationPageObj.burgerMenu.click();
            Thread.sleep(2000);
            carRegistrationPageObj.freeCarCheckLink.click();
        }
    }

    @Then("^I compare each Car Registration information to data stored in output file \"([^\"]*)\"$")
    public void i_compare_each_Car_Registration_information_to_data_stored_in_output_file(String fileName) throws Throwable {
        List<List<String>> carRecords=   carRegistrationPageObj.getAllCarRecords("car_output.txt");
        System.out.println(carRegistration.size());
        for(int i=0;i< carRegistration.size();i++)
            System.out.println(carRegistration.get(i));
        for(int counter=0;counter<carRegistration.size();counter++) {
            carRegistrationPageObj.compareCarData(carRegistration.get(counter), carMake.get(counter),
                    carModel.get(counter), carColour.get(counter), carYear.get(counter), carRecords);
        }
    }

}
