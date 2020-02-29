package au.com.coles.pageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author ujjwal keshri
 * @date 29/02/2020
 */
public class HomePage {

    WebDriver driver;
    JavascriptExecutor js;

    @FindBy(css = "span#shop-online")
    WebElement btnShopOnline;

    public HomePage(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        //This line creates all the elements of this page
        PageFactory.initElements(driver, this);
    }

    public ColesOnlinePage clickShopOnlineButton(){
        /*btnShopOnline.click();*/
        js.executeScript("arguments[0].click();", btnShopOnline);
        return new ColesOnlinePage(driver);
    }


}
