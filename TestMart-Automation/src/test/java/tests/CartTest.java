package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestMartBaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class CartTest extends TestMartBaseTest {

	protected CartPage cart ;
	protected  HomePage page;
	
	@BeforeMethod()
	public void openCartPage() {
		cart = new CartPage(getDriver());
		page = new HomePage(getDriver());
		page.gotoProductPage();
		ProductPage product = new ProductPage(getDriver());
		product.addProductsByName(page.productList());
		product.waitForSuccessCartMessageToDisappear();
		page.gotoCartPage();
		
	}
	
	@Test()
	public void verifyCartProducts() {
		Assert.assertEquals(cart.cartItemNames(), page.productList());
	}
	
	@Test()
	public void increaseItemQuantity() {
		cart.increaseCartItemsAsPerQuantity(page.productList(), page.ProductQuntities());
	}
	
	@Test()
	public void applyCoupon() {
		cart.applyCoupen();
		wait.waitForCoupenAppliedMessage();
		Assert.assertTrue(cart.isCouponApplied());
	}
	
	@Test()
	public void verifyGrandTotalAfterCouponApplied() {
		cart.applyCoupen();
		wait.waitForCoupenAppliedMessage();
		Assert.assertEquals(cart.calculateGrandTotal(), cart.grandTotal());
	}
	
	public void proceedToCheckout() {
		cart.proceedToCheckout();
	}
	
	@DataProvider(name ="Provider")
	public Object[][] getData(){
		return new Object[][] {
			{"admin" , "admin@123"} ,
			{"employee", "employee123"},
			{"user" , "user123"}
		
		};
	}
	@Test(dataProvider="Provider")
	public void print(String username , String pass) {
		System.out.println(username);
		System.out.println(pass);
		
	}
	
	
}
