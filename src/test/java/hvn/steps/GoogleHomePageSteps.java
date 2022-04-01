package hvn.steps;


import common.LoggingManager;
import common.utils.PropertyUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import hvn.pages.GoogleHomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleHomePageSteps extends BaseSteps {

    @Given("I'm on the Google search")
    public void openGoogleHomePage() {
        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.navigateTo(PropertyUtils.getEnvironmentUrlDefault());
    }

    @When("I enter {string} into search text box")
    public void searchValue(String keyword) {
        new GoogleHomePage().searchWithKeyword(keyword);
    }

    @And("I click Search button")
    public void clickSearchBtn() {
        new GoogleHomePage().clickSearchBtn();
    }

    @And("I select the first record in search result")
    public void selectFirstRecord() {
        new GoogleHomePage().clickResultIndex(1);
    }

    @Then("I verify that {string} site is at the first in search result")
    public void verifyResult(String site) {
        String actualResult = new GoogleHomePage().getResultNumberInList(1);
        boolean result = actualResult.contains(site);
        LoggingManager.assertTrue(getClassName(), result, site + " is at the first in search result", "Failed to verify search result");
    }
}
