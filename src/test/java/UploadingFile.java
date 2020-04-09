import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UploadingFile {
    public static WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
    }

    @Test
    public void fileUpload() {
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id='content']/ul/li[18]/a")).click();
        driver.findElement(By.cssSelector("#file-upload")).sendKeys("C:\\Users\\1\\Desktop\\akbar tasks.txt");
        driver.findElement(By.id("file-submit")).click();
        String actual = driver.findElement(By.cssSelector("h3")).getText();
        String expected = "File Uploaded!";
        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
