package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test
    public void validLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(driver.findElement(By.xpath("//*[@data-test='title']")).isDisplayed(),
                "Сообщение об ошибке не отображено!");
        assertEquals(driver.findElement(By.xpath("//*[@data-test='title']")).getText(),
                "Products");
    }

    @Test
    public void invalidLogin() {
        loginPage.open();
        loginPage.login("standard_user2", "secret_sauce");
        driver.findElement(By.xpath("//*[@data-test='error']")).isDisplayed();
        driver.findElement(By.xpath("//*[@data-test='error']")).getText();

        assertTrue(driver.findElement(By.xpath("//*[@data-test='error']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//*[@data-test='error']")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void lockedLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        driver.findElement(By.xpath("//*[@data-test='error']")).isDisplayed();
        driver.findElement(By.xpath("//*[@data-test='error']")).getText();

        assertTrue(driver.findElement(By.xpath("//*[@data-test='error']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//*[@data-test='error']")).getText(),
                "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user2", "");
        driver.findElement(By.xpath("//*[@data-test='error']")).isDisplayed();
        driver.findElement(By.xpath("//*[@data-test='error']")).getText();

        assertTrue(driver.findElement(By.xpath("//*[@data-test='error']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//*[@data-test='error']")).getText(),
                "Epic sadface: Password is required");
    }

    @Test
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        driver.findElement(By.xpath("//*[@data-test='error']")).isDisplayed();
        driver.findElement(By.xpath("//*[@data-test='error']")).getText();

        assertTrue(driver.findElement(By.xpath("//*[@data-test='error']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//*[@data-test='error']")).getText(),
                "Epic sadface: Username is required");
    }
}
