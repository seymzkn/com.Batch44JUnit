package Questions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.swing.StringUIClientPropertyKey;

import java.time.Duration;
import java.util.List;

public class Q5 {

    //● https://www.amazon.com/ adresine gidin.
    //            - Test 1
    //    Arama kutusunun yanindaki kategori menusundeki kategori
    //    sayisinin 45 oldugunu test edin
    //             -Test 2
    //1. Kategori menusunden Books secenegini secin
    //2. Arama kutusuna Java yazin ve aratin
    //3. Bulunan sonuc sayisini yazdirin
    //4. Sonucun Java kelimesini icerdigini test edin

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //● https://www.amazon.com/ adresine gidin.
        driver.get("https://www.amazon.com/");

    }

    @Test
    public void test01(){
        //    Arama kutusunun yanindaki kategori menusundeki kategori
        //    sayisinin 45 oldugunu test edin

        List<WebElement>kategoriler=driver.findElements(By.xpath("(//option)"));


        int expectedSayi=45;
        int actualSayi=kategoriler.size();
        //System.out.println(actualSayi);
        Assert.assertFalse(expectedSayi==actualSayi);

    }

    @Test
    public void test02() throws InterruptedException {
        //1. Kategori menusunden Books secenegini secin

        WebElement kategori=driver.findElement(By.xpath("(//option)"));
        kategori.click();

        WebElement books= driver.findElement(By.xpath("//option[@value='search-alias=stripbooks-intl-ship']"));
        books.click();

        Thread.sleep(3000);

        //2. Arama kutusuna Java yazin ve aratin
        WebElement searchBox= driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Java"+ Keys.ENTER);

        //3. Bulunan sonuc sayisini yazdirin
        WebElement actualSonucSayisiElementi= driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));

        //4. Sonucun Java kelimesini icerdigini test edin
        String actualSonucYazisi=actualSonucSayisiElementi.getText();
        String expectedKelime="Java";
        Assert.assertTrue(actualSonucYazisi.contains(expectedKelime));




    }


    @AfterClass
    public static void teardown(){
        driver.close();

    }
}
