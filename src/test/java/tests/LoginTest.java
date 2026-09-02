package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test(description = "Проверка верной авторизации", priority = 1)
    public void validLoginTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.pageIsOpen(), "Сообщение об ошибке не отображено");
        assertEquals(productsPage.getNamePage(), "Products",
                "Название страницы не соответствует ожидаемому");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user2", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"standard_user2", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"}
        };
    }

    @Test(dataProvider = "loginData", priority = 2)
    public void invalidLoginTest(String user, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMsg
        );
    }
}
