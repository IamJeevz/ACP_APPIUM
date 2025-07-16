package page_appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class waba_fetch_last_msg {

    AppiumDriver<MobileElement> driver;
    By con_row = By.xpath("//android.widget.TextView[@resource-id='com.whatsapp:id/conversations_row_contact_name' and @text='Prudent Technologies']");
    //    By last_text_row = By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/message_text\"]");
    By last_text_row_bc = By.xpath("//android.widget.TextView[contains(@resource-id, 'com.whatsapp:id') and contains(@resource-id, 'message')]");
    By last_text_row = By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/message_text\"] | //android.widget.TextView[@resource-id=\"com.whatsapp:id/description\"]");
    
    By close_sugg = By.id("com.google.android.as:id/suggestion_dismiss");
    By more_options=By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]");
    By more_dropdown=By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/title\" and @text=\"More\"]");
    By clearchats_opt=By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/title\" and @text=\"Clear chat\"]");
    By confirm_clear_chat=By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]");

    //Audio
    By download_btn=By.xpath("//android.widget.ImageButton[@content-desc=\"Download\"]");
    By download_progress=By.xpath("//android.widget.ImageButton[@content-desc=\"Download\"]");
    By file_name_sec=By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/description\"]");
    By audio_player_view=By.xpath("//android.widget.LinearLayout[@resource-id=\"com.whatsapp:id/conversation_row_audio_player_view\"]");

    public  waba_fetch_last_msg(AppiumDriver<MobileElement> driver)
    {
        this.driver=driver;
    }


    public String get_waba_last_msg() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<MobileElement> lws = driver.findElements(last_text_row);
        String final_text_path = "(//android.widget.TextView[@resource-id=\"com.whatsapp:id/message_text\"] | //android.widget.TextView[@resource-id=\"com.whatsapp:id/description\"])["+lws.size()+"]";
//        System.out.println("final_text_path="+final_text_path);
        String last_text = driver.findElement(By.xpath(final_text_path)).getText();
        return last_text;
    }




    public String get_bc_waba_last_msg() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(con_row))).click();
        Thread.sleep(3500);
        List<MobileElement> ci = driver.findElements(close_sugg);
        if (ci.size() > 0) {
            driver.findElement(close_sugg).click();
        }

        List<MobileElement> lws = driver.findElements(last_text_row_bc);
        String final_text_path = "(//android.widget.TextView[contains(@resource-id, 'com.whatsapp:id') and contains(@resource-id, 'message')]])["+lws.size()+"]";
//        System.out.println("final_text_path="+final_text_path);
        String last_text = driver.findElement(By.xpath(final_text_path)).getText();
        return last_text;
    }

    public void open_chat(String provicer_name) throws  Exception
    {
       By chat_name=By.xpath("//android.widget.TextView[@resource-id='com.whatsapp:id/conversations_row_contact_name' and @text='"+provicer_name+"']");
       WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(chat_name))).click();
        Thread.sleep(3500);
        List<MobileElement> ci = driver.findElements(close_sugg);
        if (ci.size() > 0) {
            driver.findElement(close_sugg).click();
        }
    }

    public  void clear_chats() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(more_options))).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(more_dropdown))).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(clearchats_opt))).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(confirm_clear_chat))).click();

    }

    public String fetch_audio() throws InterruptedException {
        boolean a=true;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(20000);
        List<MobileElement> audio_view_bar=driver.findElements(audio_player_view);
        if(audio_view_bar.isEmpty())
        {
            return null;
        }
        else {
            while (a)
            {
                List<MobileElement> btn_sts=driver.findElements(download_btn);
                List<MobileElement> progress_sts=driver.findElements(download_progress);
                if(!btn_sts.isEmpty())
                {
                    driver.findElement(download_btn).click();
                    Thread.sleep(3500);
                }
                else if (!progress_sts.isEmpty())
                {
                    Thread.sleep(5000);
                }
                else {
                    a=false;
                    Thread.sleep(1500);
                }
            }

            String f_name=driver.findElement(file_name_sec).getText();
            return f_name;
        }
    }

    public void fetch_image(boolean caption)
    {

    }
}
