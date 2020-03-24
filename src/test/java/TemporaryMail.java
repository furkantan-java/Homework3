import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TemporaryMail {
    private WebDriver driver;

    @Test(description = "This test is for sending mail")
    public void tempMail() throws InterruptedException {
        driver.get("https://www.tempmailaddress.com/");
        driver.manage().window().maximize();
        String email = driver.findElement(By.cssSelector("#email")).getText();
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        driver.findElement(By.cssSelector("[name=\"full_name\"]")).sendKeys("John");
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys(email);
        driver.findElement(By.cssSelector(".radius")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".subheader")).isDisplayed());
        driver.navigate().to("https://www.tempmailaddress.com/");
        String actual = driver.findElement(By.cssSelector(".from")).getText().trim();
        String expected = "do-not-reply@practice.cybertekschool.com";
        Assert.assertEquals(actual,expected);
        driver.findElement(By.cssSelector(".from")).click();
        String actual2 = driver.findElement(By.cssSelector("#odesilatel")).getText().trim();
        Assert.assertEquals(actual2,expected);
        String actualMessage = driver.findElement(By.cssSelector("#predmet")).getText().trim();
        String expectedMessage = "Thanks for subscribing to practice.cybertekschool.com!";
        Assert.assertEquals(actualMessage,expectedMessage);
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
