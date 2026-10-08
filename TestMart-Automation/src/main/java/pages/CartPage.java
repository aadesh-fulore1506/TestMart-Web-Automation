package pages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import utils.WaitUtils;

public class CartPage {

	WebDriver driver;
	WaitUtils wait ;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WaitUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//td//div/div/a")
	private List<WebElement> cartItems;

	@FindBy(id = "coupon-code")
	private WebElement couponCode;

	@FindBy(id = "apply-coupon")
	private WebElement applyCoupon;

	@FindBy(css = "#toast-container")
	private WebElement couponMsg;

	@FindBy(xpath = "//tbody/tr/td[4]")
	private List<WebElement> itemPrices;

	@FindBy(css = "dd[data-testid='summary-subtotal']")
	private WebElement summarySubtotal;

	@FindBy(css = "dd[data-testid='summary-discount']")
	private WebElement summaryDiscount;

	@FindBy(css = "dd[data-testid='summary-tax']")
	private WebElement summaryTax;

	@FindBy(css = "strong[data-testid='summary-total']")
	private WebElement summaryTotal;

	@FindBy(id = "checkout-button")
	private WebElement checkoutButton;

	@FindBy(css = "button[data-testid='cart-increase']")
	List<WebElement> cartIncreaseButton;
	
	@FindBy(css = "div[data-testid='coupon-applied']")
	WebElement successCouponMessage;

	@FindBy(id = "update-cart")
	private WebElement updateCart;

	@FindBy(css = "tr[data-testid='cart-row']")
	private List<WebElement> cartRows;

	public List<String> cartItemNames() {
		List<String> itemNames = cartItems.stream().map(items -> items.getText()).collect(Collectors.toList());
		return itemNames;
	}
	
	public boolean isCouponApplied() {
	return	successCouponMessage.isDisplayed();
	}

	By product = By.cssSelector("td div div a");
	By button = By.cssSelector("button[data-testid='cart-increase']");

	public void increaseCartItemsAsPerQuantity(List<String> products, List<Integer> quantity) {

	    for (int i = 0; i < products.size(); i++) {

	        for (int j = 1; j < quantity.get(i); j++) {

	            for (WebElement row : cartRows) {

	                String productName = row.findElement(product).getText();

	                if (productName.equals(products.get(i))) {

	                    WebElement increaseButton = row.findElement(button);
	                    increaseButton.click();

	                    break;
	                }
	            }
	        }
	    }

	    updateCart.click();
	}

	public void applyCoupen() {
		String code = couponCode.getAttribute("placeholder");
		couponCode.sendKeys(code);
		applyCoupon.click();
		wait.waitForVisible(successCouponMessage);
		
		
		
	}

	public double lineTotal() {
		double lineTotal = 0;
		for (WebElement itemPrice : itemPrices) {
			double price = Double.parseDouble(itemPrice.getText().replace("$", ""));
			lineTotal += price;
		}
		return lineTotal;
	}

	public double subTotal() {
		double subTotal = Double.parseDouble(summarySubtotal.getText().replace("$", ""));
		return subTotal;
	}

	public double calculateGrandTotal() {
		double discount = Double.parseDouble(summaryDiscount.getText().replace("-$", ""));
		double tax = Double.parseDouble(summaryTax.getText().replace("$", ""));

		double total = subTotal() + tax - discount;
		return total;

	}

	public double grandTotal() {
		double grandTotal = Double.parseDouble(summaryTotal.getText().replace("$", ""));
		return grandTotal;
	}

	public void proceedToCheckout() {
		checkoutButton.click();
	}

}
//List<WebElement> cartItems = driver.findElements(By.xpath("//td//div/div/a"));
//List<String> itemNames = cartItems.stream().map(items->items.getText()).collect(Collectors.toList());
//Assert.assertEquals(itemNames , Arrays.asList(products));
//itemNames.forEach(n-> System.out.println(n));

//driver.findElement(By.id("coupon-code")).sendKeys("SAVE10");
//driver.findElement(By.id("apply-coupon")).click();
////String discount = driver.findElement(By.cssSelector(".alert-success")).getText();
//WebElement couponMsg =driver.findElement(By.cssSelector("#toast-container"));
//wait.until(ExpectedConditions.visibilityOf(couponMsg));
//wait.until(ExpectedConditions.invisibilityOf(couponMsg));

//List<WebElement> itemPrice =driver.findElements(By.xpath("//tbody/tr/td[4]"));
//double subTotal = 0;
//for(WebElement price : itemPrice) {
//String price2 = price.getText();
//double priceValue = Double.parseDouble(price2.replace("$", ""));
//subTotal = subTotal + priceValue ;
//}
//Assert.assertEquals(subTotal , 2375.08);
//System.out.println(driver.findElement(By.cssSelector("dd[data-testid='summary-subtotal']")).getText());
//String summaryDiscount = driver.findElement(By.cssSelector("dd[data-testid='summary-discount']")).getText();
//double discount = Double.parseDouble(summaryDiscount.replace("-$", ""));
//String summaryTax = driver.findElement(By.cssSelector("dd[data-testid='summary-tax']")).getText();
//double tax = Double.parseDouble(summaryTax.replace("$", ""));
// String summaryTotal = driver.findElement(By.cssSelector("strong[data-testid='summary-total']")).getText();
//	double grandTotal = Double.parseDouble(summaryTotal.replace("$", ""));
//	double total = subTotal - discount + tax ;
//	Assert.assertEquals(grandTotal, total);
//	System.out.println(total);
//	
//driver.findElement(By.id("checkout-button")).click();
