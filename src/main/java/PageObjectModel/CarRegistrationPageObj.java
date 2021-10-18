package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarRegistrationPageObj {
    private WebDriver driver;
    private static int carRegColumn;
    private static int carMakeColumn;
    private static int carModelColumn;
    private static int carColourColumn;
    private static int carYearColumn;
    // Constructor to initiate Web Driver and Page Factory
    public CarRegistrationPageObj(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Web Elements
    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Free Car Check')]")
    public WebElement freeCarCheckHeading;
    @FindBy(how = How.ID, using = "vrm-input")
    public WebElement carRegTextField;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Free Car Check')]")
    public WebElement freeCarCheckButton;
    @FindBy(how = How.CLASS_NAME, using = "nav-toggle")
    public WebElement burgerMenu;
    @FindBy(how = How.LINK_TEXT, using = "Free Car Check")
    public WebElement freeCarCheckLink;
    @FindBy(how = How.XPATH, using = "//dt[text()='Registration']/following-sibling::dd")
    public WebElement carRegistration;
    @FindBy(how = How.XPATH, using = "//dt[text()='Make']/following-sibling::dd")
    public WebElement carMake;
    @FindBy(how = How.XPATH, using = "//dt[text()='Model']/following-sibling::dd")
    public WebElement carModel;
    @FindBy(how = How.XPATH, using = "//dt[text()='Colour']/following-sibling::dd")
    public WebElement carColour;
    @FindBy(how = How.XPATH, using = "//dt[text()='Year']/following-sibling::dd")
    public WebElement carYear;


    public void carRegistrationEntry(String carReg){
        carRegTextField.clear();
        carRegTextField.sendKeys(carReg);
        freeCarCheckButton.click();
    }
    // Method to read data from Multiple files, While passing filenames we need seperater (,)
    // e.g  if we pass  output.txt,output1.txt
    public String readFilesData(String filesName) {
        String[] files;
        if(filesName.contains(",")) {
            files = filesName.split(",");
            System.out.println("here");
        }else
            files = new String[]{filesName};
        String data = "";
        String allData="";
        for(int counter=0;counter< files.length;counter++) {
            try {
                data = new String(Files.readAllBytes(Paths.get("./src/test/resources/TestFiles/input/" + files[counter])));
            } catch (IOException e) {
                e.printStackTrace();
            }
            allData = allData + data;
        }

        return allData;
    }

    // Get All Car Registration by Comparing the pattern to all data in Files
    public List<String> getAllCarRegistration(String data, String pattern){
        List<String> carRegistrationList = new ArrayList<String>();
        Pattern carReg = Pattern.compile(pattern);
        Matcher matchedData = carReg.matcher(data);

        while (matchedData.find()) {
            carRegistrationList.add(matchedData.group());
        }
        return  carRegistrationList;
    }

    // Get List of All record from OutPut Comparison File
    public List<List<String>> getAllCarRecords(String fileName){
        String file = "./src/test/resources/TestFiles/output/"+fileName;
        String delimiter = ",";
        String eachLine;
        List<List<String>> allData = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            br.readLine(); // Ignoring Headers
            getHeaderColumnNumbers(Arrays.asList(br.readLine().split(delimiter)));
            while((eachLine = br.readLine()) != null){
                List<String> values = Arrays.asList(eachLine.split(delimiter));
                allData.add(values);
            }
            allData.forEach(l -> System.out.println(l));
        } catch (Exception e){
            System.out.println("Error while reading the File" + e.toString());
        }
        return  allData;
    }
    // Compare Output File data with data retrieve from the webpage
    public void compareCarData(String carReg, String carMake,String carModel,
                               String carColour, String carYear,List<List<String>> carData ) {
            int matchCounter=0;
            for (int i = 0; i < carData.size(); i++) {
            if(carData.get(i).get(carRegColumn).equals(carReg)){
                matchCounter++;
            System.out.println(carReg + " Car Registration Found in the output File");
            if(carData.get(i).get(carModelColumn).equals(carModel))
            System.out.println(carReg + " Model details are matching to the saved output");
            else
            System.out.println(carReg + " Model details are not matching to the saved output"
            + " Website data is Car Model "+ carModel +" , while save data is "+ carData.get(i).get(carModelColumn));
            if(carData.get(i).get(carMakeColumn).equals(carMake))
            System.out.println(carReg + " Make details are matching to the saved output");
            else
            System.out.println(carReg + " Make details are not matching to the saved output"
                    + " Website data is Car Make "+ carMake +" , while save data is "+ carData.get(i).get(carMakeColumn));
            if(carData.get(i).get(carColourColumn).equals(carColour))
            System.out.println(carReg + " car colour details are matching to the saved output");
            else
            System.out.println(carReg + " car colour details are not matching to the saved output"
                    + " Website data is Car Colour "+ carColour +" , while save data is "+ carData.get(i).get(carColourColumn));
            if(carData.get(i).get(carYearColumn).equals(carYear))
            System.out.println(carReg + " car year details are matching to the saved output");
            else
            System.out.println(carReg + " car year details are not matching to the saved output"
                    + " Website data is Car year "+ carYear +" , while save data is "+ carData.get(i).get(carYearColumn));
            }

            }
            if(matchCounter==0) System.out.println(carReg + " details are not matching out put records");
    }

     // Storing column numbers in case sequence is not same as in current File
        public void getHeaderColumnNumbers(List<String> headersData){
            for(int i=0;i<headersData.size();i++) {
                if(headersData.get(i).equals("REGISTRATION")) carRegColumn = i;
                else
                if(headersData.get(i).equals("MAKE")) carMakeColumn = i;
                else
                if(headersData.get(i).equals("MODEL")) carModelColumn = i;
                else
                if(headersData.get(i).equals("COLOR")) carColourColumn = i;
                else
                if(headersData.get(i).equals("YEAR")) carYearColumn = i;
            }
        }

  }
