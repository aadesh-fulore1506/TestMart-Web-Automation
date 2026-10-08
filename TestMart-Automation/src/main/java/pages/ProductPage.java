package pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.WaitUtils;

public class ProductPage {
	
	private final WebDriver driver ;
	WaitUtils wait ;
	public ProductPage(WebDriver driver) {
		this.driver = driver ;
	    PageFactory.initElements(driver, this);
	    this.wait = new WaitUtils(driver);
		}
	
	@FindBy(css = ".product-name")
	private List<WebElement> productNames;

	@FindBy(css = ".btn.btn-sm.add-to-cart")
	private List<WebElement> addToCartButtons;

	@FindBy(id = "prod-next")
	private WebElement nextButton;

	@FindBy(css = "div[data-testid='toast-container']")
	private WebElement successMsg;
	
	@FindBy(css=".btn-secondary.btn-sm.prod-page")
	private List<WebElement> pages;

	@FindBy(id="cart-link")
	private WebElement cart;

	@FindBy(id="cart-count")
	private WebElement cartCount;
	
	public int getProductCount() {
	    return productNames.size();
	}
	
	public int cartCount() {
		int count =Integer.parseInt( cartCount.getText());
		return count ;
	}

	public String getProductName(int index) {
	    return productNames.get(index).getText();
	}

	public void clickAddToCart(int index) {
	    addToCartButtons.get(index).click();
	}

	public void clickNextButton() {
	    nextButton.click();
	}

	    public void addProductsByName(List<String> productNamesToAdd) {

	        int addedProducts = 0;

	        for (int page = 0; page < pages.size(); page++) {

	            for (int product = 0; product < productNames.size(); product++) {

	                String name = productNames.get(product).getText().trim();

	                if (productNamesToAdd.contains(name)) {

	                    WebElement addCartButton = addToCartButtons.get(product);

	                    wait.waitForElementToBeClickable(addCartButton);
	                    addCartButton.click();

	                    addedProducts++;

	                    if (addedProducts == productNamesToAdd.size()) {
	                        return;
	                    }
	                }
	            }

	            // Move to next page only if required
	            wait.waitForElementToBeClickable(nextButton);
	            nextButton.click();
	        }
	    }

		public void waitForSuccessCartMessageToDisappear() {
			wait.waitForInvisibilityOfAllElements(successMsg);
		}
		
		public void gotoCartPage() {
			cart.click();
		}
	}



