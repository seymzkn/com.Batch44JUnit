package practise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Q5 {
    /*
   ...Exercise5...
  @BeforeClass ın icerisinde driver i kuralim
  maximize edip tum web elementler yuklenene kadar 10 sn bekletelim
  Google 'a gidelim ve sayfa basliginin Google icerdigini dogrulayalim
  Amazon'a gidelim ve url in www.amazon.com icerip icermedigini dogrulayalim
  @AfterClass ta driver ı kapatalim

   */

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void test01(){
        //Google 'a gidelim ve sayfa basliginin Google icerdigini dogrulayalim

        driver.get("https://www.google.com/");
        driver.findElement(By.xpath("//div[.='Ich stimme zu']")).click();
        String pageTitle=driver.getTitle();
        boolean iceriyorMuTitle=pageTitle.contains("Google");
        Assert.assertTrue("PageTitle Google iceriyor mu: ", iceriyorMuTitle);

        //Amazon'a gidelim ve url in www.amazon.com icerip icermedigini dogrulayalim
        driver.get("https://www.amazon.com/");
        String pageUrl=driver.getCurrentUrl();
        boolean iceriyorMuUrl=pageUrl.contains("www.amazon.com/");
        Assert.assertTrue("PageUrl www.amazon.com iceriyor mu ", iceriyorMuUrl);
    }


    @AfterClass
    public static void teardown(){
        driver.close();
    }
}
