package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ReviewOrder {

	WebDriver driver ;
	
   public ReviewOrder(WebDriver driver) {
	   this.driver = driver ;
	   PageFactory.initElements(driver, this);
   }
   
   @FindBy(xpath = "//tbody/tr/td[1]")
   List<WebElement> products;
   
   @FindBy(css = "strong[data-testid='summary-total']")
   WebElement grandTotal;
   
   @FindBy(xpath = "//tbody/tr/td[4]")
   List<WebElement> productsPrice;
   
   @FindBy(id="place-order")
   WebElement placeOrderButton ;
   
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
   
   public void placeOrder() {
	   placeOrderButton.click();
   }
}
