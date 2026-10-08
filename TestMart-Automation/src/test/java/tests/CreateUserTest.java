package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import base.TestMartBaseTest;
import pages.CreateUserPage;
import pages.HomePage;
import utils.WaitUtils;

public class CreateUserTest extends BaseTest{

	protected CreateUserPage userPage ;
	WaitUtils wait ;
	@BeforeMethod(alwaysRun = true)
	public void openCreateUserTest() {
		wait = new WaitUtils(getDriver());
		HomePage page = new HomePage(getDriver());
		page.gotoCreateUserPage();
		 userPage = new CreateUserPage(getDriver());
		 
	}
	
	
    @Test(description ="Verify Create User Page Is Displayed")
    public void verifyCreateUserPage() {
    	Assert.assertTrue(userPage.pageTitle().isDisplayed());
    }
    
    @Test(description ="register New User")
    public void registerNewUser() {
    	userPage.registerUser();
    	wait.usernameCreatedSucessMessage();
    Assert.assertTrue(userPage.registerMessageIsDisplayed());
    	
    }
    
    
}

