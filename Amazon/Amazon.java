package amazon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import POM.Login;
import POM.Search_Products;
import POM.Select_Products;


public class Amazon 
{
	WebDriver driver;
	FileInputStream file;
	Properties prop;

	@Test(priority = 1 , description = "Opening Browser")
	public void LoadUrl() throws IOException
	{

		prop=new Properties();
		file=new FileInputStream("/home/qq379/eclipse-workspace/DataDrivenXSL/src/test/java/config.properties");
		prop.load(file);

		System.out.println(prop.get("browser"));
		String browser=(String) prop.get("browser");

		//Checking Browser
		if(browser.contains("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "/home/qq379/Selenium/chromedriver");
			driver=new ChromeDriver();
		}

		String url=(String) prop.get("url");
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Test(priority = 2 , description = "Login_Page")
	public void LoginPage() throws InterruptedException
	{
		PageFactory.initElements(driver, Login.class);

		Login.login.click();

		//Login_Page_Assertion
		String title=(String) prop.get("SignInTitle");
		Assert.assertEquals(driver.getTitle().toString(), title);

		Login.Phone_Number.sendKeys("9008128107");
		Login.Continue.click();
		Login.Password.sendKeys("Divya@123");
		Login.Sign_in.click();

		//HomePage_Assertion
		Thread.sleep(5000);
		String homeTitle=(String) prop.get("HomePageTitle");
		Assert.assertEquals(driver.getTitle().toString(), homeTitle);
	}

	@Test(priority = 3 , description = "Search_Product")
	public void Search_Product() throws InterruptedException
	{
		PageFactory.initElements(driver, Search_Products.class);
		Thread.sleep(5000);

		WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox")) ;
		Assert.assertEquals(true, searchBox.isDisplayed());

		WebElement searchIcon=driver.findElement(By.id("nav-search-submit-button"));
		Assert.assertEquals(true, searchIcon.isEnabled());

		Search_Products.SearchBox.click();
		Search_Products.Search_products.sendKeys("Western Sarees");
		Search_Products.Search.click();
	}

	@Test(priority = 4 , description = "Select_Product")
	public void Select_Product() throws InterruptedException
	{
		PageFactory.initElements(driver, Select_Products.class);

		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'™ Deepika-Padukon Full Crushed Digital Printed Pure Georgette Saree With"
				+ " Unstitched Blouse Piece (Yellow)')]")));

		String text=driver.findElement(By.xpath("//span[contains(text(),'™ Deepika-Padukon Full Crushed Digital Printed Pure Georgette Saree With Unstitched "
				+ "Blouse Piece (Yellow)')]")).getText();

		String ProductText=(String) prop.get("Text");
		Assert.assertEquals(text,ProductText);

		Select_Products.SelectProd.click();
	}

	@Test(priority = 5)
	public void Buy_Now_Product()
	{
		PageFactory.initElements(driver, POM.BuyNow.class);

		String ParentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		ArrayList<String> tab = new ArrayList<String>(window);
		driver.switchTo().window(tab.get(1));


		String Buytitle=(String) prop.get("BuyNowTitle");
		Assert.assertEquals(driver.getTitle().toString(), Buytitle);

		POM.BuyNow.BuyProduct.click();

		driver.close();
		driver.quit();

	}

}
