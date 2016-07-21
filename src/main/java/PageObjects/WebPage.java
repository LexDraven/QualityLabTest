package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebPage {
    private WebDriver webDriver;

    public WebPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public boolean clickWebElement(WebElement webElement) {
        try {
            webElement.click();
            return true;
        } catch (WebDriverException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void typeText(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    public boolean waitUntilElementExists (By locator){
        try {
            new WebDriverWait(webDriver,5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
