package tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import base.TestMartBaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.WaitUtils;

public class LoginTest extends BaseTest {

	protected LoginPage loginPage ;
	
	@BeforeMethod()
	public void openLoginPage() {
		
		HomePage page = new HomePage(getDriver());
		page.gotoLogIn();
		loginPage = new LoginPage(getDriver());
	}
	
	@Test(description ="Login With Correct Credentials")
	public void loginWithCorrectCredentials() {
		loginPage.loginWithValidCredentials();
		Assert.assertTrue(loginPage.loginSuccessFully());
	}
	
	@Test(description ="Login With Invalid Credentials")
	public void loginWithInvalidCredentials() {
		loginPage.invalidLogin();
		Assert.assertTrue(loginPage.loginSuccessFully());
	}
	
	
	
	
	
	
}
