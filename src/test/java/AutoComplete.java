import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutoComplete {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    public void test() {
        driver.get("http://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.cssSelector("[href='/autocomplete']")).click();
        driver.findElement(By.cssSelector("#myCountry")).sendKeys("United States of America");
        driver.findElement(By.cssSelector("[value='Submit']")).click();
        String actual = driver.findElement(By.cssSelector("#result")).getText();
        String expected = "You selected: United States of America";
        Assert.assertEquals(actual, expected);
    }


}
