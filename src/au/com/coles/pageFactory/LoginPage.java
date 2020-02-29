package au.com.coles.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author ujjwal keshri
 * @date 29/02/2020
 */
public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "input#login-email-input")
    WebElement inputLoginEmail;

    @FindBy(css = "input#login-password-input")
    WebElement inputLoginPassword;

    @FindBy(css = "button[class*='login-form-btn-submit']")
    WebElement btnLogin;

    @FindBy(css = "a.draw-link-login")
    WebElement btnLoginSignUp;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void clickLoginSignUpButton(){
        btnLoginSignUp.click();
    }
    public void enterUserEmail(String emailId){
        wait.until(ExpectedConditions.visibilityOf(inputLoginEmail));
        inputLoginEmail.sendKeys(emailId);
    }

    public void enterUserPassword(String password){
        inputLoginPassword.sendKeys(password);
    }

    public void clickLoginButton(){
        btnLogin.click();
    }

    public void loginToColes(String userId, String password){

        this.clickLoginSignUpButton();
        this.enterUserEmail(userId);
        this.enterUserPassword(password);
        this.clickLoginButton();
    }

}
