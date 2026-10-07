package pages;

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
	
	@FindBy(xpath = "//button[@data-testid='checkout-next-1']")
	WebElement checkoutNext1;

	@FindBy(xpath = "//button[@data-testid='checkout-next-2']")
	WebElement checkoutNext2;

	@FindBy(xpath = "//input[@value='UPI']")
	WebElement upiOption;

	@FindBy(id = "upiId")
	WebElement upiId;

	@FindBy(xpath = "//button[@data-testid='checkout-next-3']")
	WebElement checkoutNext3;
	
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
	
}


