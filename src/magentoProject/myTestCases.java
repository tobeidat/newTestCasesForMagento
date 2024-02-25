package magentoProject;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases extends parameters {

	@BeforeTest
	public void setup() {
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void createAccount() throws InterruptedException {
		WebElement createAnAccount = driver
				.findElement(By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']"));
		createAnAccount.click();

		WebElement firstName = driver.findElement(By.name("firstname"));
		WebElement lastName = driver.findElement(By.name("lastname"));

		WebElement email = driver.findElement(By.cssSelector("#email_address"));
		WebElement passwordElement = driver.findElement(By.cssSelector("#password"));

		WebElement confirmPassword = driver.findElement(By.xpath("//input[@id='password-confirmation']"));

		firstName.sendKeys(firstNames[randomIndex1]);
		lastName.sendKeys(lastNames[randomIndex2]);

		email.sendKeys(userEmails);
		passwordElement.sendKeys(userPassword);
		confirmPassword.sendKeys(userPassword);

		WebElement creatAccountButton = driver.findElement(By.cssSelector("button[title='Create an Account']"));
		creatAccountButton.click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		WebElement successMsg = driver.findElement(By.className("message-success"));
		Assert.assertEquals(successMsg.getText().contains("Thank you"), true);

		Thread.sleep(3000);

	}

	@Test(priority = 2)
	public void logout() throws InterruptedException {
		driver.get(logoutPage);
		Thread.sleep(8000);
	}

	@Test(priority = 3)
	public void signIn() throws InterruptedException {
		WebElement signInButton = driver.findElement(By.cssSelector("div[class='panel header'] li[data-label='or'] a"));
		signInButton.click();

		WebElement loginEmail = driver.findElement(By.name("login[username]"));
		WebElement thePassword = driver.findElement(By.name("login[password]"));

		loginEmail.sendKeys(userEmails);
		thePassword.sendKeys(userPassword);

		WebElement signInButtonElement = driver.findElement(By.id("send2"));
		signInButtonElement.click();
		Thread.sleep(3000);

	}

	@Test(priority = 4)
	public void theSubscriptionProcess() throws InterruptedException {
		driver.get("https://softwaretestingboard.com/subscribe/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		WebElement emailAddress = driver.findElement(By.id("mce-EMAIL"));
		emailAddress.sendKeys(userEmails);

		WebElement firstName = driver.findElement(By.name("FNAME"));
		firstName.sendKeys(firstNames[randomIndex1]);

		WebElement lastName = driver.findElement(By.name("LNAME"));
		lastName.sendKeys(lastNames[randomIndex2]);

		WebElement companyName = driver.findElement(By.id("mce-COMPANY"));
		companyName.sendKeys("companyName");

		WebElement positionElement = driver.findElement(By.xpath("//input[@id='mce-POSITION']"));
		positionElement.sendKeys("Quality Assurance Engineer");

		WebElement subscribeButton = driver.findElement(By.id("mc-embedded-subscribe"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", subscribeButton);

		WebElement subscriptionMessage = driver.findElement(By.id("mce-success-response"));

		Thread.sleep(4000);

		Assert.assertEquals(subscriptionMessage.getText().contains("Almost finished"), true);
	}

	@Test(priority = 5)
	public void addLastTwoItems() throws InterruptedException {

		driver.get("https://magento.softwaretestingboard.com/gear/fitness-equipment.html");

		WebElement itemsScope = driver.findElement(By.className("product-items"));
		List<WebElement> allItems = itemsScope.findElements(By.tagName("li"));

		for (int i = (allItems.size()) - 2; i <= (allItems.size()) - 1; i++) {
			allItems.get(i).click();

			Thread.sleep(3000);
			
			WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
			JavascriptExecutor executo = (JavascriptExecutor) driver;
			executo.executeScript("arguments[0].click();", addToCartButton);
			Thread.sleep(8000);

			driver.get("https://magento.softwaretestingboard.com/gear/fitness-equipment.html");
			itemsScope = driver.findElement(By.className("product-items"));
			allItems = itemsScope.findElements(By.tagName("li"));
		}
	}

	@Test(priority = 6)
	public void sortItems() {
		driver.get(
				"https://magento.softwaretestingboard.com/women/tops-women/tanks-women.html?product_list_order=name");

		WebElement sortItem = driver.findElement(By.id("sorter"));
		Select select = new Select(sortItem);
		select.selectByValue("price");

		List<WebElement> allPrices = driver.findElements(By.className("price-wrapper"));

		double firstItemPrice = Double.parseDouble(allPrices.get(0).getText().replace("$", ""));
		double lastItemPrice = Double.parseDouble(allPrices.get(allPrices.size() - 1).getText().replace("$", ""));

		Assert.assertEquals(firstItemPrice < lastItemPrice, true);

	}

	@AfterTest
	public void postTest() {
	}

}
