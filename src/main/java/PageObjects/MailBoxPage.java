package PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailBoxPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@class='b-toolbar__item']/child::a")
    private WebElement writeLetterButton;

//    @FindBy (id = "mailbox__password")
//    private WebElement passwordInput;
//
//    @FindBy (id = "mailbox__auth__button")
//    private WebElement loginButton;


    public MailBoxPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean clickWriteLetterButton() {
        try {
            writeLetterButton.click();
            return true;
        } catch (WebDriverException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setMailAddress(String mailTo) {
        webDriver.findElement(By.xpath("//div[contains(@class,'labels_focused')]/descendant::textarea[@data-original-name='To']")).sendKeys(mailTo);
    }

    public void setSubject(String subject) {
        webDriver.findElement(By.xpath("//input[@name='Subject']")).sendKeys(subject);
    }

    public void setMainText(String text){
        WebElement frame= webDriver.findElement(By.xpath("//iframe[starts-with(@id,'compose_')]"));
        webDriver.switchTo().frame(frame);
        webDriver.findElement(By.id("tinymce")).click();
        webDriver.findElement(By.id("tinymce")).clear();
        webDriver.findElement(By.id("tinymce")).sendKeys(text);
        webDriver.switchTo().parentFrame();
    }

    public boolean sendLetter(){
        try {
            webDriver.findElement(By.xpath("//div[@data-name='send']")).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
