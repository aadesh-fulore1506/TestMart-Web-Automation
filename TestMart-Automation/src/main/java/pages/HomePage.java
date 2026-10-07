package pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
 
	WebDriver driver ;
	public HomePage(WebDriver driver) {
		this.driver =driver ;
		PageFactory.initElements(driver, this);
	}
	
	String[] products = { "Mechanical Keyboard", "Electric Kettle", "Yoga Mat Pro", "SQL Cookbook",
	"Wooden Puzzle 100pc" };
	
	Integer[] quantity = {1,2,3,4,5} ;
	
	
	public List<String> productList() {
		return Arrays.asList(products);
	}
	
	public List<Integer> ProductQuntities() {
		return Arrays.asList(quantity);
	}
	
	@FindBy(css = "a[data-testid='hero-login']")
	private WebElement logIn;
	
	@FindBy(xpath="(//a[text()='Create account'])[2]")
	private WebElement createAccount ;
	
	@FindBy(id = "nav-products")
	private WebElement productPage;  
	
	@FindBy(id="cart-link")
	private WebElement cart;
	   
	   @FindBy(id="nav-orders")
	   private WebElement orders ;
	
	public void gotoCartPage() {
		cart.click();
	}
	
	public void gotoLogIn() {
		logIn.click();
	}
	
	  public void gotoProductPage() {
		  productPage.click();
	   }
	  
	public void gotoCreateUserPage() {
		createAccount.click();
	}
	
	   public void gotoOrderPage() {
		   orders.click();
	   }
}
