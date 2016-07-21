package PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MailBoxPage extends WebPage{
    private WebDriver webDriver;
    private By messageSentSuccess = By.className("message-sent__title");
    private By letterBody = By.name("Compose");

    @FindBy(xpath = "//div[@class='b-toolbar__item']/child::a")
    private WebElement writeLetterButton;

    @FindBy (xpath = "//textarea[@data-original-name='To']")
    private WebElement addressToInput;

    @FindBy (name = "Subject")
    private WebElement letterSubject;

    @FindBy (xpath = "//iframe[starts-with(@id,'compose_')]")
    private WebElement iframeMainText;

    @FindBy (xpath = "//div[@data-name='send']")
    private WebElement sendLetterButton;

    @FindBy (id= "tinymce")
    private WebElement mainTextArea;

    public MailBoxPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public boolean clickWriteLetterButton() {
        return clickWebElement(writeLetterButton);
    }

    public void setMailAddress(String mailTo) {
        typeText(addressToInput,mailTo);
    }

    public void setSubject(String subject) {
        typeText(letterSubject,subject);
    }

    public void setMainText(String text){
        webDriver.switchTo().frame(iframeMainText);
        clickWebElement(mainTextArea);
        typeText(mainTextArea,text);
        webDriver.switchTo().parentFrame();
    }

    public boolean clickSendLetter(){
        if (!clickWebElement(sendLetterButton)) {
            return false;
        }
        return waitUntilElementExists(messageSentSuccess);
    }

    public boolean sendMail(String address, String subject, String text) {
        if (!clickWriteLetterButton()) {
            return false;
        }
        waitUntilElementExists(letterBody);
        setMailAddress(address);
        setSubject(subject);
        setMainText(text);
        return clickSendLetter();
    }


}
