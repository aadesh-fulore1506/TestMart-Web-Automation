package Testmart_Automation;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.CartPage;
import pages.CheckoutPage;
import pages.CreateUserPage;
import pages.LoginPage;
import pages.OrderPage;
import pages.ProductPage;
import pages.ReviewOrder;
import utils.WaitUtils;

public class Testmart_Automation {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:9090/testmart.html");

		String[] products = { "Mechanical Keyboard", "Electric Kettle", "Yoga Mat Pro", "SQL Cookbook",
				"Wooden Puzzle 100pc" };
		Integer[] quantity = {1,2,3,4,5} ;

		WaitUtils wait = new WaitUtils(driver);
		//LoginPage

		CreateUserPage createUser = new CreateUserPage(driver);
		createUser.gotoCreateUserPage();
		createUser.registerUser();
		wait.usernameCreatedSucessMessage();
		String username =  createUser.getUsername();
		System.out.println(username);
		createUser.gotoLoginPage();
		
		//LoginPage 
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithNewUser(username, "Raj@1234");
        wait.loginSuccessMessage();
        loginPage.gotoProductPage();
	

        //ProductPage
		ProductPage productPage = new ProductPage(driver);
		productPage.addProductsByName(products);
		productPage.waitForSuccessCartMessageToDisappear();
		productPage.gotoCartPage();
		
		
		//CartPage
		
		CartPage cartPage = new CartPage(driver);
		Assert.assertEquals(cartPage.cartItemNames(),Arrays.asList(products));
		cartPage.increaseCartItemsAsPerQuantity(products, quantity);
		cartPage.applyCoupen();
		wait.waitForCoupenAppliedMessage();
		Assert.assertEquals(cartPage.lineTotal(), cartPage.subTotal());
		Assert.assertEquals(cartPage.grandTotal(), cartPage.calculateGrandTotal());
		cartPage.proceedToCheckout();
		

	    // Checkout Page

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        String upiId = "RajWayle@Testbank";
        checkoutPage.continueToshipping();
        checkoutPage.continueToPayment();
        checkoutPage.makePayment(upiId);
        checkoutPage.reviewOrder();
        
        
        //review order 
        ReviewOrder reviewOrder = new ReviewOrder(driver);
		Assert.assertEquals(reviewOrder.productNames(), Arrays.asList(products));
		Assert.assertEquals(reviewOrder.productTotal(), cartPage.lineTotal());
		Assert.assertEquals(reviewOrder.grandTotal(), cartPage.grandTotal());
		reviewOrder.placeOrder();
        	wait.waitForOrderConfirmationMessage();
		

		//OrderPage 
		OrderPage order = new OrderPage(driver);
		order.gotoOrderPage();
		order.confirmOrderPlace();
		order.orderDetails();
		
		
		

        

        
	
	
	}
}
