package basics;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.utility.ReadProperties;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TendableTest {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	//Top menus
	String url = ReadProperties.getProperty("url");
	String aboutLocator = ReadProperties.getProperty("about");
	String productsLocator = ReadProperties.getProperty("prducts");
	String sectorsLocator = ReadProperties.getProperty("sectors");
	String contenthubLocator = ReadProperties.getProperty("contenthub");
	String contactLink = ReadProperties.getProperty("contactLink");

	//formdata
	String emailField = ReadProperties.getProperty("emailField");
	String emailValue= ReadProperties.getTestData("emailValue");
	String firstNameField=ReadProperties.getProperty("firstNameField");
	String firstName= ReadProperties.getTestData("firstName");
	String lastNameField=ReadProperties.getProperty("lastNameField");
	String lastName= ReadProperties.getTestData("lastName");
	String companyNameFiled=ReadProperties.getProperty("companyNameFiled");
	String companyName= ReadProperties.getTestData("companyName");
	String messageTypeDropdown=ReadProperties.getProperty("messageTypeDropdown");
	String checkbox =ReadProperties.getProperty("checkbox");
	String submitButton =ReadProperties.getProperty("submitButton");
	String errorMessage=ReadProperties.getProperty("errorMessage");

	@BeforeTest
	public void setUp12() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();  
		js = (JavascriptExecutor) driver;
	}

	@Test(priority = 1)
	public void testMenuAccessibilityAndRequestDemoButton() {
		String[] menuItems = {aboutLocator, productsLocator, sectorsLocator, contenthubLocator};    
		for (String menuItem : menuItems) {
			assertTrue(isMenuItemAccessible(menuItem), "Menu item not accessible: " + menuItem);
			assertTrue(isRequestDemoButtonPresentAndActive(menuItem), "Request a Demo button not found or not active on: " + menuItem);
		}
	}
	private boolean isMenuItemAccessible(String menuItem) {
		try {
			WebElement element = driver.findElement(By.linkText(menuItem));
			String expectedUrl = element.getAttribute("href");   
			element.click();  
			wait.until(ExpectedConditions.urlToBe(expectedUrl));
			return true;
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage()); 
			return false;
		}
	}
	private boolean isRequestDemoButtonPresentAndActive(String menuItem) {
		try {
			WebElement element = driver.findElement(By.linkText(menuItem));
			String expectedUrl = element.getAttribute("href");
			element.click();
			wait.until(ExpectedConditions.urlToBe(expectedUrl));
			WebElement demoButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='button is-small w-button']")));

			if (demoButton.isDisplayed() && demoButton.isEnabled()) {
				System.out.println("Request a Demo button is present and active on " + menuItem + " page.");
				return true;
			} else {
				System.out.println("Request a Demo button is not active or visible on " + menuItem + " page.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			return false;
		}
	}

	@Test (priority=2)
	public void testContactFormSubmissionWithError() throws InterruptedException {
		driver.findElement(By.linkText(contactLink)).click();
		driver.findElement(By.xpath(emailField)).sendKeys(emailValue);
		driver.findElement(By.xpath(firstNameField)).sendKeys(firstName);
		driver.findElement(By.xpath(lastNameField)).sendKeys(lastName);
		js.executeScript("window.scrollBy(0,300);");
		driver.findElement(By.xpath(companyNameFiled)).sendKeys(companyName);

		WebElement messageTypeDropdownElement = driver.findElement(By.xpath(messageTypeDropdown));
		Select messageTypeDropdown = new Select(messageTypeDropdownElement);
		messageTypeDropdown.selectByVisibleText("Marketing");

		js.executeScript("window.scrollBy(0,300);");
		driver.findElement(By.xpath(checkbox)).click();		
		driver.findElement(By.xpath(submitButton)).click();
		assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(errorMessage))).isDisplayed(), "Error message not displayed. Test failed!");

		System.out.println("Test Passed: Error message displayed as expected.");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
