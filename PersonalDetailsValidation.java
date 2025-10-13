package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class PersonalDetailsValidation {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://tutorialsninja.com/demo/");
            driver.manage().window().maximize();

            // Navigate to Register Page
            driver.findElement(By.xpath("//span[text()='My Account']")).click();
            driver.findElement(By.linkText("Register")).click();

            // ----------- Step 1: Enter valid First Name ------------
            WebElement firstName = driver.findElement(By.id("input-firstname"));
            firstName.clear();
            firstName.sendKeys("John");
            System.out.println("Entered valid First Name: John");

            // ----------- Step 2 & 3: Enter 33 chars in First Name and check validation ------------
            firstName.clear();
            String longFirstName = "A".repeat(33);  // 33 characters
            firstName.sendKeys(longFirstName);
            driver.findElement(By.xpath("//input[@value='Continue']")).click();

            WebElement firstNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")));
            if (firstNameError.isDisplayed()) {
                System.out.println("Error shown for First Name > 32 chars: " + firstNameError.getText());
            }

            // ----------- Step 4: Enter valid Last Name ------------
            WebElement lastName = driver.findElement(By.id("input-lastname"));
            lastName.clear();
            lastName.sendKeys("Doe");
            System.out.println("Entered valid Last Name: Doe");

            // ----------- Step 5 & 6: Enter 33 chars in Last Name and check validation ------------
            lastName.clear();
            String longLastName = "B".repeat(33);
            lastName.sendKeys(longLastName);
            driver.findElement(By.xpath("//input[@value='Continue']")).click();

            WebElement lastNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")));
            if (lastNameError.isDisplayed()) {
                System.out.println("Error shown for Last Name > 32 chars: " + lastNameError.getText());
            }

            // ----------- Fix first and last names before final submit ------------
            firstName.clear();
            firstName.sendKeys("John");

            lastName.clear();
            lastName.sendKeys("Doe");

            // ----------- Step 7: Enter valid Email ------------
            WebElement email = driver.findElement(By.id("input-email"));
            email.clear();
            email.sendKeys("johndoe" + System.currentTimeMillis() + "@mail.com"); // to ensure unique
            System.out.println("Entered valid Email");

            // ----------- Step 8: Enter valid Telephone (between 3 and 32) ------------
            WebElement phone = driver.findElement(By.id("input-telephone"));
            phone.clear();
            phone.sendKeys("1234567890");
            System.out.println("Entered valid Telephone");

            // ----------- Password and Confirm Password ------------
            WebElement passwordField = driver.findElement(By.id("input-password"));
            WebElement confirmPasswordField = driver.findElement(By.id("input-confirm"));

            String password = "test1234";
            String confirmPassword = "test1234";

            passwordField.clear();
            passwordField.sendKeys(password);
            confirmPasswordField.clear();
            confirmPasswordField.sendKeys(confirmPassword);

            // ----------- Newsletter 'Yes' radio button ------------
            WebElement newsletterYes = driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));
            newsletterYes.click();

            // ----------- Privacy Policy Checkbox ------------
            WebElement privacyCheckbox = driver.findElement(By.name("agree"));
            privacyCheckbox.click();

            // ----------- Click Continue button ------------
            WebElement continueBtn = driver.findElement(By.cssSelector("input[type='submit']"));
            continueBtn.click();

            // ----------- Wait and Check for success message ------------
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Your Account Has Been Created!')]")));
            if (successMsg.isDisplayed()) {
                System.out.println("✅ Account creation successful.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
