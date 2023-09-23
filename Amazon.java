import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Amazon {
    public static void main (String args []) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.amazon.in");
        driver.manage().window().maximize();

        WebElement element = driver.findElement(By.id("twotabsearchtextbox"));
        element.sendKeys("Camera");

        driver.findElement(By.cssSelector("input[type='submit']")).click();

        List<WebElement> elements = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));

        int count = 0;
        try (FileWriter fileWriter = new FileWriter("E:\\apply\\Amazon.txt")) {
            for(WebElement result: elements){
                if(count >=5){
                    break;
                }

                WebElement spanElement = driver.findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']"));
                String productName = spanElement.getText();

                WebElement priceElement = driver.findElement(By.xpath(".//span[@class='a-price-whole']"));
                String price = priceElement.getText();

                fileWriter.write("Product name "+productName+"\n");
                fileWriter.write("Price "+price+"\n\n");

                count++;



            }
            driver.quit();
        }

    }
}
