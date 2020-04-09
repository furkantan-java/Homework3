import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Registration {
    static WebDriver driver;

    @BeforeMethod
    public static void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");

    }

    @Test(description = "This test is for date of birth message for invalid input")
    public void dateOfBirth() {
        WebElement registrationForm = driver.findElement(By.linkText("Registration Form"));
        registrationForm.click();
        WebElement dateOfBirth = driver.findElement(By.xpath("//input[@class='form-control'][@name='birthday']"));
        dateOfBirth.sendKeys("wrong_dob");
        String actual = driver.findElement(By.xpath("//small[text()='The date of birth is not valid']")).getText();
        String expected = "The date of birth is not valid";
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "This test is for checking programming languages are visible")
    public void languages() {
        WebElement registrationForm = driver.findElement(By.xpath("//a[@href='/registration_form']"));
        registrationForm.click();
        List<WebElement> lst = driver.findElements(By.className("form-check-input"));
        for (WebElement each : lst) {
            Assert.assertTrue(each.isDisplayed());
        }
    }

    @Test(description = "This test is for checking if first name is valid")
    public void firstName() {
        driver.findElement(By.cssSelector("[href='/registration_form']")).click();
        driver.findElement(By.cssSelector("[name='firstname']")).sendKeys("a");
        String actual = driver.findElement(By.xpath("//small[starts-with(text(),'first name must')]")).getText();
        String expected = "first name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "This test is for checking if last name is valid")
    public void lastName() {
        driver.findElement(By.cssSelector("[href='/registration_form']")).click();
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("a");
        String actual = driver.findElement(By.xpath("//small[starts-with(text(),'The last name must')]")).getText();
        String expected = "The last name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Filling Registration Form")
    public void registrationForm() {
        driver.findElement(By.linkText("Registration Form")).click();
        driver.findElement(By.cssSelector("[name=\"firstname\"]")).sendKeys("John");
        driver.findElement(By.cssSelector("[name=\"lastname\"]")).sendKeys("Smith");
        driver.findElement(By.cssSelector("[name=\"username\"]")).sendKeys("flamingo");
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys("abc@gmail.com");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("123456Super");
        driver.findElement(By.cssSelector("[name=\"phone\"]")).sendKeys("123-123-1234");
        driver.findElement(By.cssSelector(".radio:nth-child(2) input ")).click();
        driver.findElement(By.cssSelector("[name=\"birthday\"]")).sendKeys("11/11/2000");
        Select s1 = new Select(driver.findElement(By.cssSelector("[name='department']")));
        s1.selectByIndex(5);
        Select s2 = new Select(driver.findElement(By.cssSelector("[name=\"job_title\"]")));
        s2.selectByIndex(2);
        driver.findElement(By.cssSelector(".form-check.form-check-inline:nth-child(2) input")).click();
        driver.findElement(By.cssSelector("#wooden_spoon")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h4//following-sibling::p")).isDisplayed());
    }



    @AfterMethod
    public static void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }


    }
}
