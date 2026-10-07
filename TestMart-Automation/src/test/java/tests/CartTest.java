package tests;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
		ProductPage product = new ProductPage(getDriver());
		product.addProductsByName(page.productList());
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
	
	
	
}
