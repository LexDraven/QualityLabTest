package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MailRuLoginPage {
    private WebDriver webDriver;
    private final String URL = "https://mail.ru/";
    private final By loginButtonLocator = By.id("mailbox__auth__button");

    @FindBy (id = "mailbox__login")
    private WebElement loginInput;

    @FindBy (id = "mailbox__password")
    private WebElement passwordInput;

    @FindBy (id = "mailbox__auth__button")
    private WebElement loginButton;


    public MailRuLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        if (webDriver == null ){
            webDriver =new FirefoxDriver();
        }
        if (!webDriver.getCurrentUrl().equals(URL)) {
            webDriver.get(URL);
        }
    }

    public void typeLogin(String login) {
        loginInput.clear();
        loginInput.sendKeys(login);
    }

    public void typePassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public boolean clickLoginButton() {
        try {
            loginButton.click();
            return true;
        } catch (WebDriverException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MailBoxPage submitLoginPassword(String login, String password){
        typeLogin(login);
        typePassword(password);
        Assert.assertTrue(clickLoginButton());
        try {
            new WebDriverWait(webDriver,5).until(ExpectedConditions.invisibilityOfElementLocated(loginButtonLocator));
        } catch (TimeoutException e) {
            return null;
        }
        Assert.assertTrue(isURLContains("messages/inbox"));
        return new MailBoxPage(webDriver);
    }

    public boolean isURLContains(String text) {
       return webDriver.getCurrentUrl().contains(text);
    }







}
