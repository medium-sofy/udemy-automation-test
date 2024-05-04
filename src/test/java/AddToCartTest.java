import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * 1- select search bar
 * 2- enter a course name
 * 3- select the course with matching name
 * 4- click on it
 * 5- select the add to cart button
 * 6- click on add to cart button
 * 7- select the view cart button
 * 8- click on the view cart button
 * 9- get the text of the course in the cart
 * 10- assert that the course in the cart matches the entered course
 *
 */
public class AddToCartTest {
    WebDriver driver = new FirefoxDriver();
    @BeforeTest
    public void setup(){
        driver.get("https://udemy.com");
        driver.manage().window().maximize();
    }

    @Test
    public void addToCart(){
        Actions act = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String searchQuery = "Android development";
        driver.get("https://www.udemy.com/courses/search/?src=ukw&q=" + searchQuery);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // select the course by the image
        WebElement courseImage = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div/div[2]/div[2]/div[1]/div/div/div[1]"));
        act.moveToElement(courseImage).perform();

        WebElement addToCart = driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/div/div/div[1]/div/button"));
        addToCart.click();
        // hover on cart icon to click on go to cart button
        WebElement cartIcon = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[4]/div[5]"));
        act.moveToElement(cartIcon).perform();

        WebElement goToCart = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[4]/div[5]/div/div/div/div/div/div[2]/a"));
        goToCart.click();

        List<WebElement> cartItems = driver.findElements(By.xpath("/html/body/div[1]/div[2]/div/div/div[3]/div[1]/div/div/ul[1]"));
        // test that the cart is not empty
        Assert.assertFalse(cartItems.isEmpty());


    }
}
//*[@id="form-group--1"]