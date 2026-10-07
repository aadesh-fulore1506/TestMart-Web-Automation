package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestMartBaseTest;
import pages.CreateUserPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends TestMartBaseTest {

	protected LoginPage loginPage ;
	@BeforeMethod()
	public void openLoginPage() {
		loginPage = new LoginPage(getDriver());
		HomePage page = new HomePage(getDriver());
		page.gotoLogIn();
	}
	
	@Test(description ="Login With Correct Credentials")
	public void loginWithCorrectCredentials() {
		loginPage.login();
		Assert.assertTrue(loginPage.loginSuccessFully());
	}
	
	@Test(description ="Login With Invalid Credentials")
	public void loginWithInvalidCredentials() {
		loginPage.invalidLogin();
		Assert.assertTrue(loginPage.loginSuccessFully());
	}
	
	
	
	
	
	
}
