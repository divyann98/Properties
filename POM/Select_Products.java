package POM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Select_Products 
{
	@FindBy(xpath = "//span[contains(text(),'™ Deepika-Padukon Full Crushed Digital Printed Pure Georgette Saree "
			+ "With Unstitched Blouse Piece (Yellow)')]")
	public static WebElement SelectProd;
}
