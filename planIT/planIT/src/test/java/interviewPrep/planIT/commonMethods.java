package interviewPrep.planIT;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class commonMethods {

	public static WebDriver driver1;
	public static WebDriverWait w1;
	public static String codMessage;

	public static void launchLogin() // this method launches the browser & logs into demo shop

	{

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Rajesh\\Downloads\\chromedriver_new\\chromedriver.exe");

		ChromeOptions op = new ChromeOptions();

		op.addArguments("--remote-allow-origins=*");

		// launching chrome browser with the options to avoid 403 status code error
		driver1 = new ChromeDriver(op);

		driver1.manage().window().maximize();

		// explicit wait of 20 secs
		w1 = new WebDriverWait(driver1, Duration.ofSeconds(20));

		driver1.get("http://demowebshop.tricentis.com/");

		driver1.findElement(By.xpath("//a[@href=\"/login\"]")).click();

		driver1.findElement(By.xpath("//input[@id=\"Email\"]")).sendKeys("planittest78@gmail.com");

		driver1.findElement(By.xpath("//input[@id=\"Password\"]")).sendKeys("123456");

		driver1.findElement(By.xpath("//a[contains(text(),'Forgot password?')]//following::input")).click();
	}

	public static void addProduct() // this method add a product to cart
	{

		WebElement computer = driver1.findElement(By.xpath("//a[contains(text(),'Computers')]"));

		Actions a1 = new Actions(driver1);

		a1.moveToElement(computer).build().perform();

		driver1.findElement(By.xpath("//a[contains(text(),'Desktops')]")).click();

		driver1.findElement(By.linkText("Build your own cheap computer")).click();

		String productName = driver1.findElement(By.xpath("//h1")).getText();

		String productPrice = driver1.findElement(By.xpath("//span[@itemprop=\"price\"]")).getText();

		int quantity = 3; // setting the quantity to 3

		driver1.findElement(By.xpath("//input[@id=\"addtocart_72_EnteredQuantity\"]")).clear();

		driver1.findElement(By.xpath("//input[@id=\"addtocart_72_EnteredQuantity\"]"))
				.sendKeys(Integer.toString(quantity));

		driver1.findElement(By.xpath("//input[@id=\"add-to-cart-button-72\"]")).click();

	}

	public static void checkout() // this method does the checkout
	{
		
		driver1.findElement(By.linkText("Shopping cart")).click();
		
		driver1.findElement(By.xpath("//input[@id=\"termsofservice\"]")).click();

		driver1.findElement(By.xpath("//button[@id=\"checkout\"]")).click();

		driver1.findElement(By.xpath("//input[@title=\"Continue\"]")).click();

		w1.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id=\"shipping-buttons-container\"]//following::input[1]")));

		driver1.findElement(By.xpath("//div[@id=\"shipping-buttons-container\"]//following::input[1]")).click();

		w1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"shippingoption_1\"]")));

		driver1.findElement(By.xpath("//input[@id=\"shippingoption_1\"]")).click();

		driver1.findElement(By.xpath("//div[@id=\"shipping-method-buttons-container\"]//following::input[1]")).click();

		w1.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id=\"payment-method-buttons-container\"]//following::input[1]")));

		driver1.findElement(By.xpath("//div[@id=\"payment-method-buttons-container\"]//following::input[1]")).click();

		w1.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//form[@id=\"co-payment-info-form\"]//following-sibling::p")));

		codMessage = driver1.findElement(By.xpath("//form[@id=\"co-payment-info-form\"]//following-sibling::p"))
				.getText();

		w1.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id=\"payment-info-buttons-container\"]//following::input[1]")));

		driver1.findElement(By.xpath("//div[@id=\"payment-info-buttons-container\"]//following::input[1]")).click();

		w1.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id=\"confirm-order-buttons-container\"]//following::input[1]")));

		driver1.findElement(By.xpath("//div[@id=\"confirm-order-buttons-container\"]//following::input[1]")).click();

	}

}
