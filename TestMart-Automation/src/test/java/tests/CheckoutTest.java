package tests;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestMartBaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;
import pages.ReviewOrder;

public class CheckoutTest extends TestMartBaseTest {
 protected CheckoutPage checkoutPage ;
 HomePage page ;
 double lineTotal ;
 double grandTotal ;
 @BeforeMethod()
 public void openCheckoutPage() {
	 checkoutPage = new CheckoutPage(getDriver());
	 page = new HomePage(getDriver());
	 page.gotoProductPage();
	 ProductPage product = new ProductPage(getDriver());
	 product.addProductsByName(page.productList());
		product.waitForSuccessCartMessageToDisappear();

	 product.gotoCartPage();
	 CartPage cart = new CartPage(getDriver());
	 cart.increaseCartItemsAsPerQuantity(page.productList(), page.ProductQuntities());
	 cart.applyCoupen();
	 lineTotal = cart.lineTotal();
	 grandTotal = cart.grandTotal();
	 cart.proceedToCheckout();
	 
 }
 
 @Test()
 public void verifyCehckoutPageIsDisplayed() {
	Assert.assertTrue( checkoutPage.isCheckoutTitleDisplayed());
	 }
 
 
 @Test()
 public void reviewOrder() {
	 String upi = "RajWayle@Testbank" ;
	 checkoutPage.continueToshipping();
	 checkoutPage.continueToPayment();
	 checkoutPage.makePayment(upi);
	 checkoutPage.reviewOrder();
		Assert.assertEquals(checkoutPage.productNames(), page.productList());
		Assert.assertEquals(checkoutPage.productTotal(), lineTotal);
		Assert.assertEquals(checkoutPage.grandTotal(),grandTotal);
	 
 }

//	Assert.assertEquals(reviewOrder.productNames(), Arrays.asList(products));
//	Assert.assertEquals(reviewOrder.productTotal(), cartPage.lineTotal());
//	Assert.assertEquals(reviewOrder.grandTotal(), cartPage.grandTotal());
//	reviewOrder.placeOrder();
//   	wait.waitForOrderConfirmationMessage();
 
 @Test()
 public void placeOrder() {
	 String upi = "RajWayle@Testbank" ;
	 checkoutPage.continueToshipping();
	 checkoutPage.continueToPayment();
	 checkoutPage.makePayment(upi);
	 checkoutPage.reviewOrder();
	 checkoutPage.placeButton();
	 Assert.assertTrue(checkoutPage.isCartEmpty());
	 
 }
 

 
}

