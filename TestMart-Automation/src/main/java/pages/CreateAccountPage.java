package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage {

private final	WebDriver driver ;

public CreateAccountPage(WebDriver driver) {
	this.driver = driver ;
	PageFactory.initElements(driver, this);
}

@FindBy(id = "firstName")
private WebElement firstName;

@FindBy(id = "lastName")
private WebElement lastName;

@FindBy(id = "email")
private WebElement email;

@FindBy(id = "phone")
private WebElement phone;

@FindBy(id = "dob")
private WebElement dob;

@FindBy(css = "input[value='Male']")
private WebElement maleRadioButton;

@FindBy(id = "address")
private WebElement address;

@FindBy(id = "city")
private WebElement city;

@FindBy(id = "country")
private WebElement country;

@FindBy(id = "state")
private WebElement state;

@FindBy(id = "postalCode")
private WebElement postalCode;

@FindBy(id = "password")
private WebElement password;

@FindBy(id = "confirmPassword")
private WebElement confirmPassword;

@FindBy(id = "terms")
private WebElement terms;

@FindBy(id = "register-button")
private WebElement registerButton;

@FindBy(xpath="(//a[text()='Create account'])[2]")
private WebElement createAccount ;

@FindBy(id = "register-message")
private WebElement registerMessage;

@FindBy(id = "user-menu-label")
private WebElement userMenuLabel;

@FindBy(xpath = "//a[text()='Log in']")
private WebElement loginLink;


public void gotoCreateAccountPage() {
	createAccount.click();
}
	
public void registerUser() {

    firstName.sendKeys("Raj");
    lastName.sendKeys("Wayle");
    email.sendKeys("rajwayle@gmail.com");
    phone.sendKeys("1234567890");
    dob.sendKeys("01012006");

    maleRadioButton.click();

    address.sendKeys("Kalyan, Maharashtra");
    city.sendKeys("Kalyan");

    Select selectCountry = new Select(country);
    selectCountry.selectByValue("India");

    Select selectState = new Select(state);
    selectState.selectByValue("Maharashtra");

    postalCode.sendKeys("421306");
    password.sendKeys("Raj@1234");
    confirmPassword.sendKeys("Raj@1234");

    terms.click();
    registerButton.click();
}

public String getUsername() {
 String createdMessage =	registerMessage.getText();
 String username = createdMessage.split("is")[1].split("\\.")[0].trim();
 return username ;
}

public void gotoLoginPage() {
	userMenuLabel.click();
	loginLink.click();
}
}


//driver.findElement(By.id("firstName")).sendKeys("Raj");
//driver.findElement(By.id("lastName")).sendKeys("Wayle");
//driver.findElement(By.id("email")).sendKeys("rajwayle@gmail.com");
//driver.findElement(By.id("phone")).sendKeys("1234567890");
//driver.findElement(By.id("dob")).sendKeys("01012006");
//driver.findElement(By.cssSelector("input[value='Male']")).click();
//driver.findElement(By.id("address")).sendKeys("Kalyan,Maharastra");
//driver.findElement(By.id("city")).sendKeys("Kalyan");
//WebElement country = driver.findElement(By.id("country"));
//Select selectCountry = new Select(country);
//selectCountry.selectByValue("India");
//WebElement state = driver.findElement(By.id("state"));
//Select selectState = new Select(state);
//selectState.selectByValue("Maharashtra");
//driver.findElement(By.id("postalCode")).sendKeys("421306");
//driver.findElement(By.id("password")).sendKeys("Raj@1234");
//driver.findElement(By.id("confirmPassword")).sendKeys("Raj@1234");
//driver.findElement(By.id("terms")).click();
//driver.findElement(By.cssSelector("#register-button")).click();
//driver.findElement(By.cssSelector("#user-menu-label")).click();
//driver.findElement(By.xpath("//a[text()='Log in']")).click();
//
//driver.findElement(By.id("username")).sendKeys(userName);
//driver.findElement(By.id("password")).sendKeys("Raj@1234");
//driver.findElement(By.id("login-button")).click();
