package hvn.tests;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import webui.WebDriverManager;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "hvn/steps",
        monochrome = true,
        plugin = {"json:cucumber-output/cucumber.json", "html:cucumber-output/cucumber.html", "pretty"})

public class CucumberTest extends BaseCucumberTest {


    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        WebDriverManager.closeBrowser();
    }




}