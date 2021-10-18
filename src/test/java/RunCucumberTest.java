
import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.Test;

@CucumberOptions(plugin = "json:target/cucumber/cucumber.json")
public class RunCucumberTest {

    @Test(description = "TestNGCucumberRunner to invoke Cucumber")
    public void runCukes() {
        new TestNGCucumberRunner(getClass()).runCukes();
    }
}
