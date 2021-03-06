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
    private final static int timeToWait = 5;


    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
    }

    @Test
    public void testLoginToMailRu() {
        MailRuLoginPage loginPage = new MailRuLoginPage(driver);
        Assert.assertNotNull(mailBoxPage = loginPage.submitLoginPassword("lex.draven@mail.ru", "Q123#@!w"));
        Assert.assertTrue(mailBoxPage.sendMail("lexman2@ya.ru", "Test letter", "This is the test letter"));
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
