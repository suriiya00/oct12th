package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ss {

    public static void main(String[] args) {

        // Setup Chrome Driver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // 1. Launch the URL
            String url = "http://tutorialsninja.com/demo/";
            driver.get(url);
            driver.manage().window().maximize();

            // 2. Verify title
            String expectedTitle = "Your Store";
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                System.out.println("Title verification PASSED: " + actualTitle);
            } else {
                System.out.println("Title verification FAILED: " + actualTitle);
            }

            // 3. Click on 'My Account' dropdown
            WebElement myAccount = driver.findElement(By.xpath("//span[text()='My Account']"));
            myAccount.click();

            // 4. Select 'Register' from dropdown
            WebElement registerLink = driver.findElement(By.linkText("Register"));
            registerLink.click();

            // 5. Verify the heading ‘Register Account’
            WebElement heading = driver.findElement(By.xpath("//h1[text()='Register Account']"));
            if (heading.isDisplayed()) {
                System.out.println("Register Account heading is displayed");
            } else {
                System.out.println("Register Account heading is NOT displayed");
            }

            // 6. Click on 'Continue' button without filling any fields
            WebElement continueBtn = driver.findElement(By.xpath("//input[@value='Continue']"));
            continueBtn.click();

            // 7. Verify warning message about privacy policy
            WebElement warningMsg = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
            String warningText = warningMsg.getText();

            if (warningText.contains("You must agree to the Privacy Policy")) {
                System.out.println("Warning message verified: " + warningText);
            } else {
                System.out.println("Warning message NOT found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
//        finally {
//            // Close browser
//            driver.quit();
//        }
    }
}
