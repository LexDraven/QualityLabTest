package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;



public class MailRuLoginPage extends WebPage {
    private WebDriver webDriver;
    private final String URL = "https://mail.ru/";
    private final By mailBoxBody = By.id("ScrollBody");

    @FindBy (id = "mailbox__login")
    private WebElement loginInput;

    @FindBy (id = "mailbox__password")
    private WebElement passwordInput;

    @FindBy (id = "mailbox__auth__button")
    private WebElement loginButton;

    public MailRuLoginPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        if (webDriver == null ){
            webDriver =new FirefoxDriver();
        }
        if (!webDriver.getCurrentUrl().equals(URL)) {
            webDriver.get(URL);
        }
    }

    public void typeLogin(String login) {
        typeText(loginInput,login);
    }

    public void typePassword(String password) {
        typeText(passwordInput,password);
    }

    public boolean clickLoginButton() {
        return clickWebElement(loginButton);
    }

    public MailBoxPage submitLoginPassword(String login, String password){
        typeLogin(login);
        typePassword(password);
        Assert.assertTrue(clickLoginButton());
        Assert.assertTrue(waitUntilElementExists(mailBoxBody));
        Assert.assertTrue(isURLContains("messages/inbox"));
        return new MailBoxPage(webDriver);
    }

    public boolean isURLContains(String text) {
       return webDriver.getCurrentUrl().contains(text);
    }

}
