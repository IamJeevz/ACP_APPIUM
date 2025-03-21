package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class WhatsAppTest {
    AppiumDriver<MobileElement> driver;
    By con_row = By.xpath("//android.widget.TextView[@resource-id='com.whatsapp:id/conversations_row_contact_name' and @text='Prudent Technologies']");
//    By last_text_row = By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/message_text\"]");
    By last_text_row = By.xpath("//android.widget.TextView[contains(@resource-id, 'com.whatsapp:id') and contains(@resource-id, 'message')]");
    By close_sugg = By.id("com.google.android.as:id/suggestion_dismiss");

    @BeforeTest
    public void AppiumbaseSetup() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();


        cap.setCapability("deviceName", "POCO C65");
        cap.setCapability("udid", "SS7L4TKFQSGEQSDI");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "14.0");
        cap.setCapability("automationName", "UIAutomator2");
        cap.setCapability("appPackage", "com.whatsapp");
        cap.setCapability("appActivity", "com.whatsapp.HomeActivity");
        cap.setCapability("fullReset", false);
        cap.setCapability("noReset", true);
        cap.setCapability("appium:forceAppLaunch", true);

        driver = new AppiumDriver<>(new URL("http://127.0.0.1:4723"), cap);

        System.out.println("APPIUM CONNECTED");

    }

    @Test
    public void run() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(con_row))).click();
        Thread.sleep(2500);
        List<MobileElement> ci = driver.findElements(close_sugg);
        if (ci.size() > 0) {
            driver.findElement(close_sugg).click();
        }

        List<MobileElement> lws = driver.findElements(last_text_row);
        String final_text_path = "(//android.widget.TextView[contains(@resource-id, 'com.whatsapp:id') and contains(@resource-id, 'message')])["+lws.size()+"]";
        String last_text = driver.findElement(By.xpath(final_text_path)).getText();
        System.out.println("The last Text is: " + last_text);
    }

    @AfterTest
    public void tear()
    {
        driver.quit();
    }
}