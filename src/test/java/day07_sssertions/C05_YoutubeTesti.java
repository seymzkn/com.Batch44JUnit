package day07_sssertions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C05_YoutubeTesti {

    //1) Bir class oluşturun: YoutubeAssertions
    //2) https://www.youtube.com adresine gidin
    //3) Aşağıdaki adları kullanarak 4 test metodu oluşturun ve gerekli testleri yapin
    //    ○ titleTest   => Sayfa başlığının “YouTube” oldugunu test edin
    //    ○ imageTest   => YouTube resminin görüntülendiğini (isDisplayed()) test edin
    //     ○ Search Box 'in erisilebilir oldugunu test edin (isEnabled())
    //    ○ wrongTitleTest  => Sayfa basliginin “youtube” olmadigini dogrulayin

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //2) https://www.youtube.com adresine gidin
        driver.get("https://www.youtube.com");
        driver.findElement(By.xpath("//yt-formatted-string[text()='Ich stimme zu']")).click();


    }

    @Test
    public void titleTest(){
        //    ○ titleTest   => Sayfa başlığının “YouTube” oldugunu test edin
        String actualTitle= driver.getTitle();
        String expectedTitle="YouTube";

        Assert.assertEquals(expectedTitle,actualTitle);


    }

    @Test
    public void imageTest(){
        //    ○ imageTest   => YouTube resminin görüntülendiğini (isDisplayed()) test edin
        WebElement imageElementi= driver.findElement(By.xpath("(//div[@class='style-scope ytd-topbar-logo-renderer'])[1]"));
        Assert.assertTrue(imageElementi.isDisplayed());

    }

    @Test
    public void searchBoxTest(){
        //     ○ Search Box 'in erisilebilir oldugunu test edin (isEnabled())
        WebElement searchBoxElementi= driver.findElement(By.xpath("//div[@id='search-input']"));
        Assert.assertTrue(searchBoxElementi.isEnabled());

    }

    @Test
    public void wrongTitleTest(){
        //    ○ wrongTitleTest  => Sayfa basliginin “youtube” olmadigini dogrulayin

        String actualYoutubeTitle=driver.getTitle();
        String expectedYoutubeTitle="Youtube";
        Assert.assertFalse(actualYoutubeTitle.contains(expectedYoutubeTitle));


    }

    @AfterClass
    public static void teardown(){
        driver.close();

        
    }
}
