package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestMartBaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.OrderPage;
import pages.ProductPage;

public class OrderConfirmationTest extends TestMartBaseTest{

	protected OrderPage order ;
	 HomePage page ;
	
	@BeforeMethod()
	public void openOrderPage() {
	order = new OrderPage(getDriver());
	 page = new HomePage(getDriver());
	 ProductPage product = new ProductPage(getDriver());
	 page.gotoProductPage();
	 product.addProductsByName(page.productList());
		product.waitForSuccessCartMessageToDisappear();
	 product.gotoCartPage();
	 CartPage cart = new CartPage(getDriver());
	 cart.increaseCartItemsAsPerQuantity(page.productList(), page.ProductQuntities());
	 cart.applyCoupen();
	 cart.proceedToCheckout();
	 CheckoutPage checkout = new CheckoutPage(getDriver());
	 checkout.placeOrder();
		wait.waitForOrderConfirmationMessage();
	 page.gotoOrderPage();
	}
	
	@Test()
	public void verifyOrderIsPlaced() {
		Assert.assertTrue( order.isOrderPlaced());
	
	}
	
	
	
	
}
//order.confirmOrderPlace();
//order.orderDetails();