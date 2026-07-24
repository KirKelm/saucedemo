import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    @Test
    public void login() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");

        browser.findElement(By.xpath("//*[@data-test='username']")).sendKeys("standard_user");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce123");
        browser.findElement(By.xpath("//*[@data-test='login-button']")).click();
//        browser.findElement(By.xpath("//*[@data-test='error']")).isDisplayed();
//        assertTrue(browser.findElement(By.xpath("//*[@data-test='error']")).isDisplayed());
//dfdg
        boolean isErrorDisplayed = browser.findElement(By.xpath("//*[@data-test='error']")).isDisplayed();
        String errorMessage = browser.findElement(By.xpath("//*[@data-test='error']")).getText();

        assertTrue(isErrorDisplayed);
        assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");

        browser.quit();
    }
}
