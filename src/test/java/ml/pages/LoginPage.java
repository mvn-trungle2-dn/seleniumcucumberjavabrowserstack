package ml.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webui.WebDriverManager;
import webui.pages.BasePage;

public class LoginPage extends BasePage {
    public LoginPage() {super();}

    @FindBy(css = "input[type=email]")
    public WebElement edtEmail;

    @FindBy(css = "input[type=password]")
    public WebElement edtPassword;

    @FindBy(className = "btn")
    public WebElement btnLogin;



    public void navigateTo(String url) {
        WebDriverManager.loadUrl("https://portal-stg.monstar-lab.vn/auth/login");
        waitForPageLoaded();
    }

    public void loginWithEmailAndPassword(String email, String password) {
        edtEmail.sendKeys(email);
        edtPassword.sendKeys(password);
        btnLogin.click();
    }

}
