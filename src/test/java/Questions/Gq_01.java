package Questions;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.nashorn.internal.AssertsEnabled;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Gq_01 {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.navigate().to("http://practice.automationtesting.in/");
    }

    @Test
    public void arrivalsTest() {

        //3) Click on Shop Menu
        driver.findElement(By.xpath("//a[text()='Shop']")).click();

        //4) Now click on Home menu button
        driver.findElement(By.xpath("//a[text()='Home']")).click();

        //5) Test whether the Home page has Three Arrivals only
        //6) The Home page must contains only three Arrivals
        List<WebElement> arrivals = driver.findElements(By.className("woocommerce-LoopProduct-link"));
        int expectedArrivals = 3;
        int actualArrivals = arrivals.size();
        Assert.assertTrue(expectedArrivals == actualArrivals);
        //7) Now click the image in the Arrivals
        driver.findElement(By.xpath("//img[@title='Mastering JavaScript']")).click();

        //8) Test whether it is navigating to next page where the user can add that book into his basket.
        //9) Image should be clickable and shoul navigate to next page where user can add that book to his basket
        WebElement text = driver.findElement(By.xpath("//div[@itemprop='description']"));

        //  Assert.assertTrue(text.isDisplayed());  // --> true   // failed
        System.out.println(text.getText());
        String actualText = text.getText();
        String expectedText = "It would seem that everything that needs to be written about JavaScript has been written. Frankly, it is difficult to find a topic related to JavaScript that has not been discussed ad nauseam.";
        Assert.assertTrue(expectedText.equals(actualText));

        //10) Click on the Add To Basket button which adds that book to your basket
        //11) User can view that Book in the Menu item with price.
        //12) User can add a book by clicking on Add To Basket button which adds that book in to his Basket
        driver.findElement(By.xpath("//button[text()='Add to basket']")).click();
        driver.findElement(By.xpath("//a[@class='button wc-forward']")).click();

        //13) Select more books than the books in stock.Ex if the stock has 20 books, try to add 21.
        //14) Click the add to basket button
        // 15) Now it throws an error prompt like you must enter a value between 1 and 20
        WebElement quantity = driver.findElement(By.xpath("//input[@type='number']"));
        quantity.click();
        quantity.clear();
        quantity.sendKeys("9216");
        WebElement updateButon = driver.findElement(By.xpath("//input[@name='update_cart']"));
        updateButon.click();
        Assert.assertFalse(updateButon.isSelected());
    }

    @AfterClass
    public static void teardown() {
        driver.close();
    }
}
