package Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Helper {
    public static WebDriver initiateChromeBrowser() {
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--disable-infobars");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setJavascriptEnabled(true);
        options.merge(cap);
        options.addArguments(Arrays.asList("--ignore-certificate-errors"));
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "/src/main/resources/webDrivers/macchromedriver");
        driver=  new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        return  driver;
    }

}
