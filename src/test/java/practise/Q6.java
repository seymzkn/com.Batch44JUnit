package practise;

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
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Q6 {
     /*
   ...Exercise6...
    // 1. Amazon.com'a gidelim.
    // 2. DropDown üzerinde Books secelim.(All yazan yerde)
    //    kategorilerin hepsini konsola yazdiralim
    // 3. Arama kutusuna Les Miserables yazalım ve arama yapalim.
    // 4. Sonuc sayisini ekrana yazdiralim.
    // 5. Sonucların Les Miserables i icerdigini assert edelim
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
        // 1. Amazon.com'a gidelim.
        driver.get("https://www.amazon.com/");

        // 2. DropDown üzerinde Books secelim.(All yazan yerde)
        WebElement dropDownElementi= driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select=new Select(dropDownElementi);

        List<WebElement>options=select.getOptions();

        select.selectByVisibleText("Bücher");
        System.out.println(select.getFirstSelectedOption().getText());

        //    kategorilerin hepsini konsola yazdiralim

        for (WebElement each: options
             ) {
            System.out.println("Kategoriler : " + each.getText());

        }

        // 3. Arama kutusuna Les Miserables yazalım ve arama yapalim.
        WebElement searchBox= driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Les Miserables" + Keys.ENTER);

        // 4. Sonuc sayisini ekrana yazdiralim.

        String resultText=driver.findElement(By.xpath("(//div[@class='sg-col-inner'])[1]")).getText();
        System.out.println("result = " + resultText);

        // 5. Sonucların Les Miserables i icerdigini assert edelim
        boolean iceriyorMu=resultText.contains("Les Miserables");

        Assert.assertTrue("Sonuclar Les Miserables iceriyor mu",iceriyorMu);



    }

    @AfterClass
    public static void teardown(){
        //driver.close();
    }
}
