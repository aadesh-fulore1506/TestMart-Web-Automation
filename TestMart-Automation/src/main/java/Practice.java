import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Practice {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		driver.get("http://localhost:9090/testmart.html");
		driver.findElement(By.id("nav-playground")).click();
		driver.findElement(By.id("simple-button")).click();

		Actions action = new Actions(driver);
		WebElement doubleClick = driver.findElement(By.id("double-click-button"));
		WebElement rightClick = driver.findElement(By.id("right-click-button"));
	
        
		action.doubleClick(doubleClick).build().perform();
		String text = driver.findElement(By.id("button-output")).getText();
		Assert.assertEquals(text, "Double click registered");
		action.contextClick(rightClick).build().perform();
		String text2 = driver.findElement(By.id("button-output")).getText();
		Assert.assertEquals(text2, "Right click (contextmenu) registered");

		driver.findElement(By.id("delayed-appear")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("delayed-element"))));
		action.moveToElement(driver.findElement(By.id("hover-trigger")));
		
		WebElement ele3 = driver.findElement(By.id("pg-select"));
		Select select = new Select(ele3);
		select.selectByValue("IN");
		
		driver.findElement(By.id("check-automation")).click();
		driver.findElement(By.id("check-manual")).click();
		driver.findElement(By.xpath("//input[@data-testid='pg-radio-intermediate']")).click();
		driver.findElement(By.xpath("//button[@data-testid='pg-submit']")).click();
		driver.findElement(By.id("simple-alert")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.id("confirm-alert")).click();
		driver.switchTo().alert().accept();
		
	   WebElement frame = driver.findElement(By.id("single-frame"));
	   driver.switchTo().frame(frame);
	   driver.findElement(By.id("frame-input")).sendKeys("AADESH");
	  driver.switchTo().defaultContent();
	  
	   WebElement frame2 = driver.findElement(By.id("nested-frame"));
	   driver.switchTo().frame(frame2);

	   WebElement frame3 = driver.findElement(By.id("inner-frame"));
	   driver.switchTo().frame(frame3);
	   driver.findElement(By.id("inner-input")).sendKeys("AADESH");
	  driver.switchTo().defaultContent();
	  
	  WebElement drag = driver.findElement(By.id("html5-drag"));
	  WebElement drop = driver.findElement(By.id("html5-drop"));
	  action.dragAndDrop(drag, drop);
	  WebElement click = driver.findElement(By.cssSelector("#click-hold"));
	  action.clickAndHold(click).pause(Duration.ofSeconds(3)).release().build().perform();
	  
	  WebElement field = driver.findElement(By.id("key-input"));
	  
	  action.click(field).keyDown(Keys.CONTROL).sendKeys("a").keyDown(Keys.CONTROL).build().perform();
	  WebElement table= driver.findElement(By.id("scroll-box"));
	   JavascriptExecutor js = (JavascriptExecutor)driver ;
	   js.executeScript("window.srollBy(0,500);");
	   js.executeScript("arguments[0].scrollTop += 500;", table);

	   

	   

	  
		
		

	}

}



