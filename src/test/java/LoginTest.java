import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest {
    WebDriver browser = new ChromeDriver();

    @BeforeMethod
    public void setup() {
        browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
    }

    @Test
    public void validLogin() {
        browser.findElement(By.xpath("//*[@data-test='username']")).sendKeys("standard_user");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        browser.findElement(By.xpath("//*[@data-test='login-button']")).click();

        assertTrue(browser.findElement(By.xpath("//*[@data-test='title']")).isDisplayed(),
                "Сообщение об ошибке не отображено!");
        assertEquals(browser.findElement(By.xpath("//*[@data-test='title']")).getText(),
                "Products");
    }

    @Test
    public void invalidLogin() {
        browser.findElement(By.xpath("//*[@data-test='username']")).sendKeys("standard_user2");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        browser.findElement(By.xpath("//*[@data-test='login-button']")).click();
        browser.findElement(By.xpath("//*[@data-test='error']")).isDisplayed();
        browser.findElement(By.xpath("//*[@data-test='error']")).getText();

        assertTrue(browser.findElement(By.xpath("//*[@data-test='error']")).isDisplayed());
        assertEquals(browser.findElement(By.xpath("//*[@data-test='error']")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void emptyPassword() {
        browser.findElement(By.xpath("//*[@data-test='username']")).sendKeys("standard_user2");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("");
        browser.findElement(By.xpath("//*[@data-test='login-button']")).click();
        browser.findElement(By.xpath("//*[@data-test='error']")).isDisplayed();
        browser.findElement(By.xpath("//*[@data-test='error']")).getText();

        assertTrue(browser.findElement(By.xpath("//*[@data-test='error']")).isDisplayed());
        assertEquals(browser.findElement(By.xpath("//*[@data-test='error']")).getText(),
                "Epic sadface: Password is required");
    }

    @Test
    public void emptyLogin() {
        browser.findElement(By.xpath("//*[@data-test='username']")).sendKeys("");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        browser.findElement(By.xpath("//*[@data-test='login-button']")).click();
        browser.findElement(By.xpath("//*[@data-test='error']")).isDisplayed();
        browser.findElement(By.xpath("//*[@data-test='error']")).getText();

        assertTrue(browser.findElement(By.xpath("//*[@data-test='error']")).isDisplayed());
        assertEquals(browser.findElement(By.xpath("//*[@data-test='error']")).getText(),
                "Epic sadface: Username is required");
    }

    @AfterMethod
    public void close() {
        browser.quit();
    }
}
