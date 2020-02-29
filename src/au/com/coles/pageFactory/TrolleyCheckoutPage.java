package au.com.coles.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author ujjwal keshri
 * @date 29/02/2020
 */
public class TrolleyCheckoutPage {

    WebDriver driver;

    @FindBy(css = "span.dcf-total-price")
    WebElement cartTotalPrice;

    public TrolleyCheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean verifyTotalCartPrice(){
        String expected = "$7.18";
        return expected.equals(cartTotalPrice.getText());
    }

}
