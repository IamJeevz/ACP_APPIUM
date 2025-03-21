package page_appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class waba_fetch_last_msg {

    AppiumDriver<MobileElement> driver;
    By con_row = By.xpath("//android.widget.TextView[@resource-id='com.whatsapp:id/conversations_row_contact_name' and @text='Prudent Technologies']");
    //    By last_text_row = By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/message_text\"]");
//    By last_text_row = By.xpath("//android.widget.TextView[contains(@resource-id, 'com.whatsapp:id') and contains(@resource-id, 'message')]");
    By last_text_row = By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/message_text\"] | //android.widget.TextView[@resource-id=\"com.whatsapp:id/description\"]");
    By close_sugg = By.id("com.google.android.as:id/suggestion_dismiss");

    public  waba_fetch_last_msg(AppiumDriver<MobileElement> driver)
    {
        this.driver=driver;
    }

    public String get_waba_last_msg() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(con_row))).click();
        Thread.sleep(3500);
        List<MobileElement> ci = driver.findElements(close_sugg);
        if (ci.size() > 0) {
            driver.findElement(close_sugg).click();
        }

        List<MobileElement> lws = driver.findElements(last_text_row);
        String final_text_path = "(//android.widget.TextView[@resource-id=\"com.whatsapp:id/message_text\"] | //android.widget.TextView[@resource-id=\"com.whatsapp:id/description\"])["+lws.size()+"]";
//        System.out.println("final_text_path="+final_text_path);
        String last_text = driver.findElement(By.xpath(final_text_path)).getText();
        return last_text;
    }
}
