package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestMartBaseTest;
import pages.CreateUserPage;

public class CreateUserTest extends TestMartBaseTest{

	protected CreateUserPage userPage ;
	@BeforeMethod(alwaysRun = true)
	public void openCreateUserTest() {
		 userPage = new CreateUserPage(getDriver());
	}
	
	
    @Test(description ="Verify Create User Page Is Displayed")
    public void verifyCreateUserPage() {
    	userPage.gotoCreateUserPage();
    	Assert.assertTrue(userPage.pageTitle().isDisplayed());
    }
    
    @Test(description ="register New User")
    public void registerNewUser() {
    	userPage.registerUser();
    	wait.usernameCreatedSucessMessage();
    Assert.assertTrue(userPage.registerMessageIsDisplayed());
    	
    }
    
    
}

