package pages;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage {

	private final WebDriver driver ;
	public LoginPage(WebDriver driver) {
		this.driver = driver ;
		}
	
	@FindBy(id = "username")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "login-button")
	private WebElement loginButton;
	
	@FindBy(id = "nav-products")
	private WebElement productPage;
	
	public void login(String name , String pass) {
		username.sendKeys(name);
		password.sendKeys(pass);
		loginButton.click();
	}
	
	public void gotoProductPage() {
		productPage.click();
	}
}
