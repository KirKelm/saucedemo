package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test(description = "Проверка верной авторизации")
    public void validLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.pageIsOpen(), "Сообщение об ошибке не отображено");
        assertEquals(productsPage.getNamePage(), "Products",
                "Название страницы не соответствует ожидаемому");
    }

    @Test
    public void invalidLogin() {
        loginPage.open();
        loginPage.login("standard_user2", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void lockedLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user2", "");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Password is required");
    }

    @Test
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Username is required");
    }
}
