import PageObjects.MailBoxPage;
import PageObjects.MailRuLoginPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainTest {
    private static WebDriver driver;
    private MailBoxPage mailBoxPage;


    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testLoginToMailRu() {
        MailRuLoginPage loginPage = new MailRuLoginPage(driver);
        Assert.assertNotNull(mailBoxPage = loginPage.submitLoginPassword("lex.draven@mail.ru","Q123#@!w"));
        mailBoxPage.clickWriteLetterButton();
        mailBoxPage.setMailAddress("lexman2@ya.ru");
        mailBoxPage.setSubject("Test letter");
        mailBoxPage.setMainText("This is a test letter");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mailBoxPage.sendLetter();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public static void tearDown() {
        if (driver!=null) {
            driver.quit();
        }
    }
}
