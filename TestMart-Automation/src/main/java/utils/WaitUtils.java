package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
private final WebDriver driver ;
WebDriverWait wait ;

public WaitUtils(WebDriver driver) {
	this.driver = driver ;
	PageFactory.initElements(driver, this);
	this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
@FindBy(css="#toast-container")
private WebElement userCreatedSucessMessage ;

@FindBy(css="#toast-container")
private WebElement loginSuccessMessage ;

public void waitForElementToBeClickable(WebElement findby) {
	wait.until(ExpectedConditions.elementToBeClickable(findby));
}

public void waitForInvisibilityOfAllElements(WebElement findby) {
	wait.until(ExpectedConditions.invisibilityOfAllElements(findby));
}

public void waitForVisible(WebElement findby) {
	wait.until(ExpectedConditions.visibilityOf(findby));
}

public void waitForInvisible(WebElement findby) {
	wait.until(ExpectedConditions.invisibilityOf(findby));
}

public void usernameCreatedSucessMessage() {
	waitForVisible(userCreatedSucessMessage);
	waitForInvisible(userCreatedSucessMessage);
}

public void loginSuccessMessage() {
	waitForVisible(loginSuccessMessage);
	waitForInvisible(loginSuccessMessage);
}
}
