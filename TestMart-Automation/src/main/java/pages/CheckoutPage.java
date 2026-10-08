package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	WebDriver driver ;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[.='Continue to shipping']")
	WebElement checkoutNext1;

	@FindBy(xpath = "//button[@data-testid='checkout-next-2']")
	WebElement checkoutNext2;

	@FindBy(xpath = "//input[@value='UPI']")
	WebElement upiOption;

	@FindBy(id = "upiId")
	WebElement upiId;

	@FindBy(xpath = "//button[@data-testid='checkout-next-3']")
	WebElement checkoutNext3;
	
	@FindBy(css=".muted")
    WebElement emptyCart ;	
	
	@FindBy(id="page-title")
	WebElement pageTitle ;
	
	  @FindBy(xpath = "//tbody/tr/td[1]")
	   List<WebElement> products;
	   
	   @FindBy(css = "strong[data-testid='summary-total']")
	   WebElement grandTotal;
	   
	   @FindBy(xpath = "//tbody/tr/td[4]")
	   List<WebElement> productsPrice;
	   
	   @FindBy(id="place-order")
	   WebElement placeOrderButton ;
	
	public boolean isCheckoutTitleDisplayed() {
		return pageTitle.isDisplayed();
	}
	
	public boolean isCartEmpty() {
		return emptyCart.isDisplayed();
	}
	
	
	public void continueToshipping() {
		checkoutNext1.click();
	}
	
	public void continueToPayment() {
		checkoutNext2.click();
	}
	
	public void makePayment(String ID) {
		upiOption.click();
		upiId.sendKeys(ID);
	}
	
	public void reviewOrder() {
		checkoutNext3.click();
	}
	
	  
	   public List<String> productNames(){
		   ArrayList<String> productNames = new ArrayList<>();
		   for(WebElement product : products) {
			   productNames.add(product.getText().split("\\(")[0].trim());
		   }
		   return productNames ;
	   }
	   
	   public double productTotal() {
		   double total = 0 ;
		   for(WebElement productPrice : productsPrice) {
			  double price = Double.parseDouble(productPrice.getText().replace("$", ""));
			   total += price;
		   }
		   return total ;
	   }
	   
	   public double grandTotal() {
		  double  total = Double.parseDouble(grandTotal.getText().replace("$", ""));
		  return total ;
	   }
	   
	   public void placeButton() {
		   placeOrderButton.click();
	   }
	   
	   public void placeOrder() {
		   String upi = "RajWayle@Testbank";
		   continueToshipping();
		   continueToPayment();
		   makePayment(upi);
		   reviewOrder();
		   placeButton();
		   
	   }
	}




