package POM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login 
{
	@FindBy(id = "nav-link-accountList-nav-line-1")
	public static WebElement login;

	@FindBy(name = "email")
	public static WebElement Phone_Number;

	@FindBy(id = "continue")
	public static WebElement Continue;

	@FindBy(name = "password")
	public static WebElement Password;

	@FindBy(id = "signInSubmit")
	public static WebElement Sign_in;
}
