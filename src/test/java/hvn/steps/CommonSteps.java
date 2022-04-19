package hvn.steps;
//
import common.utils.PropertyUtils;

import io.cucumber.java.*;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import webui.WebDriverManager;


import java.util.Collection;

public class CommonSteps extends BaseSteps {

    @Before(value = "@CloseBrowserBefore", order = 1) // before hooks run in incremental order
    public static void tearDownBrowserBefore() {
        WebDriverManager.closeBrowser();
    }

    @Before(order = 2) // before hooks run in incremental order
    public static void setUpTest(Scenario scenario) {
        System.out.println("****** STARTING TEST: ******\n " + scenario.getId() + " - " + scenario.getName() + "\n");
        determineIfScenarioShouldBeSkipped(scenario);
        openNewBrowserIfNecessary();
    }

    @After(order = 2) // after hooks run in decremental order
    public static void tearDownTest(Scenario scenario) {
        addScreenshotToScenario(scenario);
    }

    @After(value = "@CloseBrowserAfter", order = 2) // after hooks run in decremental order
    public static void tearDownBrowser() {
        WebDriverManager.closeBrowser();
    }

    private static Collection<String> getScenarioTags(Scenario scenario) {
        return scenario.getSourceTagNames();
    }

    private static void determineIfScenarioShouldBeSkipped(Scenario scenario) {
        Assume.assumeFalse("Scenario should be skipped", shouldScenarioBeSkipped(scenario));
    }

    private static Boolean shouldScenarioBeSkipped(Scenario scenario) {
        String[] skipTags = PropertyUtils.getSkipTags().split(",");
        Collection<String> scenarioTags = getScenarioTags(scenario);
        for (String scenarioTag : scenarioTags) {
            for (String skipTag : skipTags) {
                if (scenarioTag.equalsIgnoreCase(skipTag.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void openNewBrowserIfNecessary() {
        if (PropertyUtils.isBrowserValid() && !WebDriverManager.isDriverSessionActive()) {
            WebDriverManager.openNewBrowser();
            WebDriverManager.maximizeWindow();
        }
    }

    private static Boolean scenarioPassed(Scenario scenario) {
        return scenario.getStatus() == Status.PASSED;
    }

    private static Boolean scenarioFailed(Scenario scenario) {
        return scenario.getStatus() == Status.FAILED;
    }

    private static void addScreenshotToScenario(Scenario scenario) {
        if (scenarioPassed(scenario) || scenarioFailed(scenario)) {
            if (PropertyUtils.isBrowserValid() && WebDriverManager.isDriverSessionActive()) {
                        final byte[] screenshot = ((TakesScreenshot) WebDriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot");
            }
        }
    }
}
