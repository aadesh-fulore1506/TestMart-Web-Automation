package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestMartBaseTest;
import pages.HomePage;
import pages.ProductPage;

public class ProductTest extends TestMartBaseTest {

	protected ProductPage productPage ;
	protected HomePage page ;
	
	@BeforeMethod()
	public void openProductPage() {
		productPage = new ProductPage(getDriver());
	 page = new HomePage(getDriver());
		page.gotoProductPage();
	}
	
	
	@Test(description = "Add Products To Cart By ProductName")
	public void addProducts() {
	
	
		productPage.addProductsByName(page.productList());
		productPage.waitForSuccessCartMessageToDisappear();
		int cartCount = productPage.cartCount();
		Assert.assertEquals(cartCount,page.productList() );
	}
	
	
	
	
	
	
}
