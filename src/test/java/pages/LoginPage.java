package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameInput = By.xpath(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordInput = By.xpath(DATA_TEST_PATTERN.formatted("password"));
    private final By loginBtn = By.xpath(DATA_TEST_PATTERN.formatted("login-button"));
    private final By error = By.xpath(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(final String userName, final String password) {
        driver.findElement(usernameInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    public String getErrorText() {
        return driver.findElement(error).getText();
    }
}
