package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

	WebDriver driver ;
	
	   public OrderPage(WebDriver driver) {
		   this.driver = driver ;
		   PageFactory.initElements(driver, this);
	   }
	   
	   @FindBy(id="nav-orders")
	   private WebElement orders ;
	   
	   @FindBy(xpath="//tbody/tr")
	   private List<WebElement> orderRows ;
	   
	   @FindBy(css="a[data-testid='order-link']")
	   private WebElement  orderId ;

	   @FindBy(css="td[data-testid='cell-product']")
	   private WebElement  products ;
	   
	   @FindBy(css="td[data-testid='cell-amount']")
	   private WebElement  amount  ;
	   
	   @FindBy(css="span[data-testid='order-status']")
	   private WebElement  orderStatus ;
	   
	   
	   public void gotoOrderPage() {
		   orders.click();
	   }
	   
	   public void confirmOrderPlace() {
		   if( orderRows.size() >0) {
			    System.out.println("Order Place Successfully");
		   }else {
			   System.out.println("Order not Placed");
		   }
	   }
	   
	   public void orderDetails() {
		System.out.println("Order ID : "+ orderId.getText() +
		                   "\nProducts : "+ products.getText() +
		                   "\nAmount : "+ amount.getText() +
		                   "\nStatus : " + orderStatus.getText()
		);
	   }
} 
	   
