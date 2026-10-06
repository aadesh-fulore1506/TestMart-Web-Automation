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

	@FindBy(css = ".toast-success")
	private WebElement successMsg;
	
	@FindBy(css=".btn-secondary.btn-sm.prod-page")
	private List<WebElement> pages;

	
	public int getProductCount() {
	    return productNames.size();
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
	public void addProductsByName(String[] productnames) {
		 int k =0 ;
		for(int page=0; page<pages.size();page++) {
			
			for (int product=0; product <productNames.size();product++) {
				
				String name = productNames.get(product).getText();
				
				if(Arrays.asList(productnames).equals(name)) {
					
				WebElement addCartButton = addToCartButtons.get(product) ;
					 wait.waitForElementToBeClickable(addCartButton);
					 addCartButton.click();
					 k++ ;
					 
					 if( k == productnames.length ) {
						 break ;
				}
			}
		}
			 if( k == productnames.length ) {
				 break ;
	}
	
			 nextButton.click();
}
	}

		public void waitForSuccessCartMessageToDisappear() {
			wait.waitForInvisibilityOfAllElements(successMsg);
		}
	}



