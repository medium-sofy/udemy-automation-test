import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

/*
 * 1- Select login button
 * 2- Click login button
 * 3- select login with another account
 * 4- click login with another account
 * 5- select email input
 * 6- enter email
 * 7- select password input
 * 8- enter wrong password
 * 9- select login button
 * 10- click login
 * 11- select failed login alert
 * 12- assert that alert contains message: "There was a problem logging in. Check your email and password or create an account."
 * */
public class FailedLoginTest {
    WebDriver driver = new FirefoxDriver();
    @BeforeTest
    public void setup(){
        driver.get("https://udemy.com");
        driver.manage().window().maximize();
    }

    @Test
    public void testFailedLogin(){
        // wait for initial page load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement homeLoginButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[4]/div[6]"));
        homeLoginButton.click();

        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"form-group--1\"]"));
        emailInput.sendKeys("wojomoj978@gmail.com");

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"form-group--3\"]"));
        passwordInput.sendKeys("medosofy");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/form/button"));
        loginButton.click();

        WebElement errorAlert = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/form/div[1]/div/div/h2/div"));
        String error = errorAlert.getText();

        Assert.assertEquals(error,"There was a problem logging in. Check your email and password or create an account.");
    }
}
