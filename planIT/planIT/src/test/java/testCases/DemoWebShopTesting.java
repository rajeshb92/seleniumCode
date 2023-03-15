package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import interviewPrep.planIT.commonMethods;

public class DemoWebShopTesting extends commonMethods {
	@BeforeTest
	public void login() {
		launchLogin();
	}

	@Test(priority = 1)
	public void validateTheUserAccountID() {

		String userAccountID = driver1.findElement(By.linkText("planittest78@gmail.com")).getText();

		Assert.assertEquals(userAccountID.equalsIgnoreCase("planittest78@gmail.com"), true);

	}

	@Test(priority = 2)
	public void validateTheMessageWhenProductIsAddedToCart() {
		addProduct();

		w1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class=\"content\"]")));

		String successMessage = driver1.findElement(By.xpath("//p[@class=\"content\"]")).getText();

		Assert.assertEquals(successMessage.equalsIgnoreCase("The product has been added to your shopping cart"), true);
	}

	@Test(priority = 3)
	public void validateCODMessage() {
		checkout();

		Assert.assertEquals(codMessage.equals("You will pay by COD"), true);

	}

	@Test(priority = 4)
	public void validateOrderSuccessMessage() {

		w1.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/checkout/completed/"));

		String orderConfirmationMessage = driver1.findElement(By.xpath("//h1//following::strong")).getText();

		String orderNumber = driver1.findElement(By.xpath("//strong//following::li[1]")).getText();

		Assert.assertEquals(orderConfirmationMessage.equalsIgnoreCase("Your order has been successfully processed!"),
				true);

		System.out.println("Your order number is " + orderNumber.substring(12));

	}

	@AfterTest
	public void tearDown() {
		driver1.close();
	}

}
