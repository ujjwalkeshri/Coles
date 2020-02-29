package au.com.coles.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author ujjwal keshri
 * @date 29/02/2020
 */
public class ColesOnlinePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "div.popup-inside")
    WebElement popup;

    @FindBy(css = "span.item-label")
    WebElement labelPopupLoggedInUser;

    @FindBy(css = "button[data-ng-click='close()']")
    WebElement btnContinueShopping;

    @FindBy(css = "input#searchTerm")
    WebElement inputSearch;

    @FindBy(css = "button#btnSearch")
    WebElement btnSearch;

    @FindBy(css = "div.product-main-info")
    List<WebElement> searchedProductList;

    @FindBy(css = "button[id*='_down']")
    List<WebElement> btnChooseQuantity;

    @FindBy(css = "button[class*='button-measure']")
    List<WebElement> btnQuantity;

    @FindBy(css = "a[class*='draw-link-cart']")
    WebElement btnTrolley;

    @FindBy(css = "a.product-image-link")
    List<WebElement> listOfProducts;

    public ColesOnlinePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public String getUserLoggedInPopupMessage(){
        return labelPopupLoggedInUser.getText().trim();
    }

    public boolean verifyLoggedInUser(){
        String expected = "You have been logged in as UJJWAL KESHRI";
        return expected.equals(getUserLoggedInPopupMessage());
    }

    public void clickContinueShopping(){
        btnContinueShopping.click();
    }

    public void enterSearchText(String product){
        inputSearch.sendKeys(product);
    }

    public void clickSearchProductButton(){
        btnSearch.click();
    }

    public void searchProduct(String product){
        this.enterSearchText(product);
        this.clickSearchProductButton();
    }

    public List<WebElement> getListOfSearchedResultProducts(){
        return listOfProducts;
    }

    public String getProductDetails(String productDescription){
        String productDetails="";
        for (WebElement listOfProduct : listOfProducts) {
            if (listOfProduct.getAttribute("textContent").contains(productDescription)) {
                listOfProduct.click();
                break;
            }
        }
        return productDetails;
    }

    public boolean verifyProductDetails(String productDescription){
        boolean isProductFound = false;
        for (WebElement listOfProduct : listOfProducts) {
            if (listOfProduct.getAttribute("textContent").contains(productDescription)) {
                isProductFound = true;
            }
        }
        return isProductFound;
    }



    public String[] getRequiredProductDetails(String productDescription){
        String productDetails="";
        String elementNumber="0";
        for(int i=0;i<searchedProductList.size();i++){
            if(searchedProductList.get(i).getAttribute("textContent").contains(productDescription)) {
                productDetails = searchedProductList.get(i).getAttribute("textContent");
                elementNumber = String.valueOf(i);
                break;
            }
        }
        return new String[]{productDetails, elementNumber};
    }

    public int getSearchedProductLocation(String productDescription){
       int productLocation=-1;
        for(int i=0;i<searchedProductList.size();i++){
            if(searchedProductList.get(i).getAttribute("textContent").contains(productDescription)) {
                productLocation = i;
                break;
            }
        }
        return productLocation;
    }

    public void selectProductQuantity(String qty, String productDescription){
        int elementNumber = getSearchedProductLocation(productDescription);
        btnChooseQuantity.get(elementNumber).click();
        wait.until(ExpectedConditions.visibilityOf(btnQuantity.get(0)));
        for(WebElement qtyElem : btnQuantity){
            if(qtyElem.getAttribute("textContent").contains(qty+" for")){
                qtyElem.click();
                break;
            }
        }
    }

    public TrolleyCheckoutPage goToCheckout(){
        btnTrolley.click();
        return new TrolleyCheckoutPage(driver);
    }

}
