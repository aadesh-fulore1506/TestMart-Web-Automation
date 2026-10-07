package pages;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

	private final WebDriver driver ;
	public LoginPage(WebDriver driver) {
		this.driver = driver ;
		PageFactory.initElements(driver, this);
		}
	@FindBy(id = "login-error")
	private WebElement loginError;
	
	@FindBy(id = "username")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "login-button")
	private WebElement loginButton;
	
	@FindBy(id = "nav-products")
	private WebElement productPage;  
	
	@FindBy(xpath = "//summary[.='Quick fill test accounts']")
	private WebElement testsAccount; 
	
	
	@FindBy(css = "button[data-user='jdoe']")
	private WebElement jdoeLogin; 
	
	@FindBy(css = "a[data-testid='hero-login']")
	private WebElement logIn; 
	
	@FindBy(css="#toast-container")
	private WebElement loginSuccessMessage ;
	
	public void loginWithNewUser(String name , String pass) {
		username.sendKeys(name);
		password.sendKeys(pass);
		loginButton.click();
	}
	
	public void login() {
		testsAccount.click();
		jdoeLogin.click();
		loginButton.click();
	}
	
	public void invalidLogin() {
		username.sendKeys("jdoe");
		password.sendKeys("12345678");
		loginButton.click();
	}
	
	public boolean loginSuccessFully() {
		 return loginSuccessMessage.isDisplayed();
	}
	
	public boolean invalidCredentials() {
		 return loginError.isDisplayed();
	}
	
	
	public void gotoLogIn() {
		logIn.click();
	}
}
