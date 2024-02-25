package magentoProject;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class parameters {
	WebDriver driver = new ChromeDriver();
	String logoutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";

	String[] firstNames = { "yazeed", "ruba", "samar", "yazan" };
	String[] lastNames = { "saif", "zaid", "ahmad", "mohammad" };
	Random rand = new Random();

	int randomIndex1 = rand.nextInt(firstNames.length);
	int randomIndex2 = rand.nextInt(lastNames.length);

	String userEmails = firstNames[randomIndex1] + lastNames[randomIndex2] + rand.nextInt(9999) + "@gmail.com";
	String userPassword = "passBass123";

}
