package hvn.tests;

import common.LoggingManager;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class BaseCucumberTest {
    protected final TestNGCucumberRunner testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

    public BaseCucumberTest() {
    }

    @BeforeClass(
            alwaysRun = true
    )

    protected void setUpClass() {
        LoggingManager.logInfo(String.valueOf(getClass()), "setUpClass");
    }

    @DataProvider
    protected Object[][] scenarios() {
        return this.testNGCucumberRunner.provideScenarios();
    }

    @Test(
            dataProvider = "scenarios"
    )
    public void scenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
        this.runScenario(pickleWrapper);
    }


    private void runScenario(PickleWrapper pickleWrapper) {
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @AfterClass(
            alwaysRun = true
    )
    protected void tearDownClass() {
        this.testNGCucumberRunner.finish();
    }


}
