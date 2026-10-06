package Testmart_Automation;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.CreateAccountPage;
import pages.LoginPage;
import utils.WaitUtils;

public class Testmart_Automation {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("http://localhost:9090/testmart.html");

		String[] products = { "Mechanical Keyboard", "Electric Kettle", "Yoga Mat Pro", "SQL Cookbook",
				"Wooden Puzzle 100pc" };
		

		WaitUtils wait = new WaitUtils(driver);
		//LoginPage

		CreateAccountPage createAccountPage = new CreateAccountPage(driver);
		createAccountPage.gotoCreateAccountPage();
		createAccountPage.registerUser();
		wait.usernameCreatedSucessMessage();
		String username =  createAccountPage.getUsername();
		createAccountPage.gotoLoginPage();
		
		//LoginPage 
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, "dwadwe");
        wait.loginSuccessMessage();
        loginPage.gotoProductPage();
	

		
		int k = 0;

		for (int page = 0; page < 5; page++) {

		    List<WebElement> productNames =
		            driver.findElements(By.cssSelector(".product-name"));

		    for (int j = 0; j < productNames.size(); j++) {

		        String name = driver.findElements(
		                By.cssSelector(".product-name")
		        ).get(j).getText();

		      

		        if (Arrays.asList(products).contains(name)) {

		            List<WebElement> buttons =
		                    driver.findElements(
		                            By.cssSelector(".btn.btn-sm.add-to-cart")
		                    );

		            WebElement button = buttons.get(j);

		            wait.until(ExpectedConditions.elementToBeClickable(button));

		            button.click();

		            System.out.println("ADDED: " + name);

		            k++;

		            if (k == products.length) {
		                break;
		            }
		        }
		    }

		    if (k == products.length) {
		        break;
		    }

		    driver.findElement(By.id("prod-next")).click();
		}

		System.out.println("Total products added: " + k);
		List<WebElement>successMsg=driver.findElements(By.cssSelector(".toast-success"));
		
		wait.until(ExpectedConditions.invisibilityOfAllElements(successMsg));
		
		
		
		
		
		
		
		
		
		driver.findElement(By.id("cart-link")).click();
		List<WebElement> cartItems = driver.findElements(By.xpath("//td//div/div/a"));
		List<String> itemNames = cartItems.stream().map(items->items.getText()).collect(Collectors.toList());
		Assert.assertEquals(itemNames , Arrays.asList(products));
		itemNames.forEach(n-> System.out.println(n));
		
		driver.findElement(By.id("coupon-code")).sendKeys("SAVE10");
		driver.findElement(By.id("apply-coupon")).click();
		//String discount = driver.findElement(By.cssSelector(".alert-success")).getText();
		WebElement couponMsg =driver.findElement(By.cssSelector("#toast-container"));
		wait.until(ExpectedConditions.visibilityOf(couponMsg));
		wait.until(ExpectedConditions.invisibilityOf(couponMsg));
		
		
	List<WebElement> itemPrice =driver.findElements(By.xpath("//tbody/tr/td[4]"));
	double subTotal = 0;
	for(WebElement price : itemPrice) {
		String price2 = price.getText();
		double priceValue = Double.parseDouble(price2.replace("$", ""));
		subTotal = subTotal + priceValue ;
		}
	Assert.assertEquals(subTotal , 2375.08);
	System.out.println(driver.findElement(By.cssSelector("dd[data-testid='summary-subtotal']")).getText());
    String summaryDiscount = driver.findElement(By.cssSelector("dd[data-testid='summary-discount']")).getText();
	double discount = Double.parseDouble(summaryDiscount.replace("-$", ""));
    String summaryTax = driver.findElement(By.cssSelector("dd[data-testid='summary-tax']")).getText();
		double tax = Double.parseDouble(summaryTax.replace("$", ""));
		 String summaryTotal = driver.findElement(By.cssSelector("strong[data-testid='summary-total']")).getText();
			double grandTotal = Double.parseDouble(summaryTotal.replace("$", ""));
			double total = subTotal - discount + tax ;
			Assert.assertEquals(grandTotal, total);
			System.out.println(total);
			
	driver.findElement(By.id("checkout-button")).click();
	
	//CheckOut-Page
	driver.findElement(By.xpath("//button[@data-testid='checkout-next-1']")).click();
	driver.findElement(By.xpath("//button[@data-testid='checkout-next-2']")).click();
	driver.findElement(By.xpath("//input[@value='UPI']")).click();
	driver.findElement(By.id("upiId")).sendKeys("rajwayle@testbank");
	driver.findElement(By.xpath("//button[@data-testid='checkout-next-3']")).click();
	
	List<WebElement> Products = driver.findElements(By.xpath("//tbody/tr/td[1]"));
	List<String> verifyProducts =Products.stream().map(n-> n.getText().split("\\(")[0].trim()).collect(Collectors.toList());
	Assert.assertEquals(verifyProducts, Arrays.asList(products));
	System.out.println(verifyProducts);
	
	
	}
}
