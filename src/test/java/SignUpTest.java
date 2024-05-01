import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

/*
* 1- click sign up [x]
* 2- get name text box [x]
* 3- enter some name [x]
* 4- get email text box [x]
* 5- enter some email [x]
* 6- get password text box [x]
* 7- enter some password [x]
* 8- get signup button [x]
* 9- click signup [x]
* 10 - skip onboarding questions [x]
* 11 - assert username equals entered username (user signed up successfully)
* */
public class SignUpTest {
    WebDriver driver = new FirefoxDriver();

    @BeforeTest
    public void setup(){
        driver.get("https://www.udemy.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void signUpTest(){

        // Wait for initial page load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement homeSignUpButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[4]/div[7]/a"));
        homeSignUpButton.click();

        WebElement nameInput = driver.findElement(By.cssSelector("#form-group--1"));
        nameInput.sendKeys("John Doe");

        WebElement emailInput = driver.findElement(By.cssSelector("#form-group--3"));
        emailInput.sendKeys("dsssssjff073@gmail.com");

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"form-group--5\"]"));
        passwordInput.sendKeys("test123");
        // submit credentials
        WebElement signUpButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/form/button"));
        signUpButton.click();

        WebElement skipQuestionsButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/main/div[2]/a"));
        skipQuestionsButton.click();
        // Wait for home page load after signup
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement profilePic = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[4]/div[9]"));
        profilePic.click();

        // Extract text of user profile and compare it with user credentials
        WebElement user = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]"));
        String username = user.getText();
        Assert.assertEquals(username, "John Doe");

    }

    @AfterTest
    public void closeUrl(){
        driver.quit();
    }
}
