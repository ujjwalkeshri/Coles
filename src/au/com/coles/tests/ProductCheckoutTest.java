package au.com.coles.tests;

import au.com.coles.pageFactory.ColesOnlinePage;
import au.com.coles.pageFactory.HomePage;
import au.com.coles.pageFactory.LoginPage;
import au.com.coles.pageFactory.TrolleyCheckoutPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ujjwal keshri
 * @date 29/02/2020
 */
public class ProductCheckoutTest {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    String baseUrl = "https://www.coles.com.au/";


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver32.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);

        /*System.setProperty("webdriver.gecko.driver", "./lib/geckodriver.exe");
        driver = new FirefoxDriver();*/
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    public void productCheckout() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        String mainWindow = driver.getWindowHandle();
        ColesOnlinePage colesOnlinePage = homePage.clickShopOnlineButton();

        //switching to next tab
        Set<String> allWindows = driver.getWindowHandles();
        for(String childWindow : allWindows){
            if(!mainWindow.equals(childWindow))
                driver.switchTo().window(childWindow);
        }

        //logged in to Coles web application
        loginPage.loginToColes("*****", "*****");

        //Verify User Logged In
        Assert.assertTrue("Logged In user details doesn't match", colesOnlinePage.verifyLoggedInUser());

        colesOnlinePage.searchProduct("Coles Full Cream Milk");

        //Assert that product we looked for is in search result
        Assert.assertTrue("Searched product not found ",
                colesOnlinePage.verifyProductDetails("Coles Full Cream Milk 3L"));

        //select the desired product and add quantity to cart
        colesOnlinePage.selectProductQuantity("2","Coles Full Cream Milk 3L");


        //Go to checkout and verify the total price
        TrolleyCheckoutPage checkoutPage = colesOnlinePage.goToCheckout();
        Assert.assertTrue("Total price of product is not matching",checkoutPage.verifyTotalCartPrice());

    }
}
