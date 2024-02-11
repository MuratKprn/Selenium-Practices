package Tests;

import Utilities.ReusableMethods;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Test01 {

    static Faker faker = new Faker();

    public static void main(String[] args) {

        // 1. Launch browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // 2. Navigate to url 'http://automationexercise.com'
        driver.navigate().to("http://automationexercise.com");

        // 3. Verify that home page is visible successfully
        WebElement home = driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));
        String expectedTitle="Automation Exercise";
        if (home.isDisplayed() && (expectedTitle.contains(driver.getTitle()))){
            System.out.println("Home page is visible");;
        }else{
            System.out.println("Home page is NOT visible");;
        }

        // 4. Click on 'Signup / Login' button
        ReusableMethods.wait(1);
        driver.findElement(By.partialLinkText("Login")).click();

        // 5. Verify 'Login to your account' is visible
        WebElement verify = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
        if (verify.isDisplayed()){
            System.out.println("'Login to your account' is visible");
        }else{
            System.out.println("'Login to your account' is NOT visible");
        }
        // 6. Enter correct email address and password
        driver.findElement(By.name("email")).sendKeys(faker.internet().emailAddress());
        driver.findElement(By.name("password")).sendKeys(faker.internet().password());

        // 7. Click 'login' button
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        // 8. Verify that 'Your email or password is incorrect!' is visible
        WebElement loggedIn = driver.findElement(By.xpath("//p[@style='color: red;']"));
        if (loggedIn.isDisplayed()){
            System.out.println("'Your email or password is incorrect!' is visible");
        }else{
            System.out.println("'Your email or password is incorrect!' is NOT visible");
        }

        driver.quit();
    }
}
