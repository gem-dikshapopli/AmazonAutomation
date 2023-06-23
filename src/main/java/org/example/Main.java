package org.example;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import javax.swing.*;
import java.time.Duration;
import java.util.*;

/*
open amazon.in
sign-in with dummy account
search for books
compare first 5 books for lowest price and highest rating
whichever book has highest rating and lowest price

 */
class Amazon {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        try{

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            driver.get("https://www.google.com/");
            //------------------------To maximize the window----------------------------//
            driver.manage().window().maximize();
            Thread.sleep(1000);
            //--------------------To find the search bar using id----------------------//
            WebElement input = driver.findElement(By.id("APjFqb"));
            //--------------------In the Search Bar it is going to Write Amazon and press Enter----//
            input.sendKeys("Amazon" + Keys.ENTER);
            //------------------click the Ist Link-----------------//
            driver.findElement(By.className("sVXRqc")).click();
            //------------------In the Search Bar It is Going to write Books and going to press enter-----//
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Books"+Keys.ENTER);
            driver.findElement(By.id("a-autoid-0-announce")).click();
            driver.findElement(By.id("s-result-sort-select_1")).click();

            //-----------------js is the Object of JavascriptExecutor used to scroll the screen upto the particular element-----------//
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            WebElement span=driver.findElement(By.xpath("//li[@id='p_72/1318476031']"));
            span.click();
            javascriptExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("(//span[@class='a-icon-alt'])[1]")));

            //--------------comapre is the TreeMap used to store the maximum people rating and name of the book---------------//

            TreeMap<String,String> compare_books=new TreeMap<>();
            for(int i=1;i<=5;i++) {
                compare_books.put((driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])["+i+"]")).getText()),driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])["+i+"]")).getText());
            }

            //-------------It is going to sort that one book which has the highest rating and lowest price-----------------//
            String last_key=compare_books.lastKey();

            //---------------------Name of The desired book-------------------------//
            System.out.println("Book name = "+compare_books.get(last_key));

            String book_name=compare_books.get(last_key);
            
            WebElement x_path = null;

            for(int l=1;l<=5;l++){
                if(last_key.equals(driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])["+l+"]")).getText())){
                    x_path=driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])["+l+"]"));
                }

            }

            javascriptExecutor.executeScript("arguments[0].scrollIntoView();", x_path);
            x_path.click();

            ArrayList<String> links=new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(links.get(1));

            Thread.sleep(1000);

           driver.findElement(By.xpath("//button[@id='add-to-ebooks-cart-button']")).click();


            
        }catch (Exception e){
            System.out.println(e);
        }


    }
}