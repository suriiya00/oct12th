package sample; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class s1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php?");
		driver.manage().window().maximize();
		String title=driver.getTitle();
		if(title.equals("Your Store"))
		{
			System.out.println("Title is matched");
		}
		
		else
		{
			System.out.println("Title is not matched");
			
		}
    
    
    
    
    
    
		
		driver.findElement(By.partialLinkText("Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click();
		
		String warning=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		
		System.out.println("Warning message is:"+warning);
		
		if(warning.equals("You must agree to the Privacy Policy!"))
		{
			System.out.println("warning is matched");
		}
		
		else
		{
			System.out.println("warning is not matched");  
		}
		
		WebElement subs=driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));
		
		if(subs.isSelected())
		{
			System.out.println("yes is selected");
		}
		else
		{
			System.out.println("yes is not selected");
		}
	}
}
