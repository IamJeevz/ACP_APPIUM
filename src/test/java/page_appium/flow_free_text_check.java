package page_appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class flow_free_text_check {
    AppiumDriver<MobileElement> driver;
    By con_row = By.xpath("//android.widget.TextView[@resource-id='com.whatsapp:id/conversations_row_contact_name' and @text='Prudent Technologies']");
    //By last_text_row = By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/message_text\"]");
    By last_text_row = By.xpath("//android.widget.TextView[contains(@resource-id, 'com.whatsapp:id') and contains(@resource-id, 'message')]");
    By close_sugg = By.id("com.google.android.as:id/suggestion_dismiss");

    By msg_entry_field=By.id("com.whatsapp:id/entry");
    By msg_send_btn=By.id("com.whatsapp:id/send");


    public  flow_free_text_check(AppiumDriver<MobileElement> driver)
    {
        this.driver=driver;
    }

    public void open_flow_chat() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(con_row))).click();
        Thread.sleep(2500);
        List<MobileElement> ci = driver.findElements(close_sugg);
        if (ci.size() > 0) {
            driver.findElement(close_sugg).click();
        }
    }

    public void sent_msg(String msg)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(msg_entry_field))).click();
        driver.findElement(msg_entry_field).sendKeys(msg);
        driver.findElement(msg_send_btn).click();

    }

    public String get_waba_last_msg() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(4000);
        List<MobileElement> ci = driver.findElements(close_sugg);
        if (ci.size() > 0) {
            driver.findElement(close_sugg).click();
        }
        List<MobileElement> lws = driver.findElements(last_text_row);
        String final_text_path = "(//android.widget.TextView[contains(@resource-id, 'com.whatsapp:id') and contains(@resource-id, 'message')])["+lws.size()+"]";
//        String final_text_path = "(//android.widget.TextView[@resource-id=\\\"com.whatsapp:id/message_text\\\"] | //android.widget.TextView[@resource-id=\\\"com.whatsapp:id/description\\\"])[\"+lws.size()+\"]";
        String last_text = driver.findElement(By.xpath(final_text_path)).getText();
        return last_text;
    }


    public String get_waba_2last_msg() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(4000);
        List<MobileElement> ci = driver.findElements(close_sugg);
        if (ci.size() > 0) {
            driver.findElement(close_sugg).click();
        }
        List<MobileElement> lws = driver.findElements(last_text_row);
        String final_text_path = "(//android.widget.TextView[contains(@resource-id, 'com.whatsapp:id') and contains(@resource-id, 'message')])["+(lws.size()-1)+"]";
//        String final_text_path = "(//android.widget.TextView[@resource-id=\\\"com.whatsapp:id/message_text\\\"] | //android.widget.TextView[@resource-id=\\\"com.whatsapp:id/description\\\"])[\"+lws.size()+\"]";
        String last2_text = driver.findElement(By.xpath(final_text_path)).getText();
        return last2_text;
    }

    /** Valid object Response  */

    public void ft_ob_01(String prev_msg)
    {
        if(prev_msg.contains("Enter any text"))
        {
            sent_msg("This is a free Text Sample msg");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 1");
        }
    }

    public  void ft_ob_02(String prev_msg)
    {
        if(prev_msg.contains("enter alphabets only"))
        {
            sent_msg("Automatic Communication Platform");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 2");
        }
    }

    public  void ft_ob_03(String prev_msg)
    {
        if(prev_msg.contains("Enter alphabets with length between 4 and 10"))
        {
            sent_msg("Whatsapp");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 3");
        }
    }
    public  void ft_ob_04(String prev_msg)
    {
        if(prev_msg.contains("Alphabets start with pru"))
        {
            sent_msg("Prupay");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 4");
        }
    }
    public  void ft_ob_05(String prev_msg)
    {
        if(prev_msg.contains("Exact alphabet - Prudent"))
        {
            sent_msg("Prudent");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 5");
        }
    }
    public  void ft_ob_06(String prev_msg)
    {
        if(prev_msg.contains("Case sensitive PRUdENT"))
        {
            sent_msg("PRUdENT");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 6");
        }
    }

    /** Invalid object Response */

    public void ft_in_ob_01(String prev_msg)
    {
        if(prev_msg.contains("Enter any text"))
        {
            sent_msg("This is a test Message to test Invalid Response");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 1");
        }
    }

    public void ft_in_ob_02(String prev_msg)
    {
        if(prev_msg.contains("enter alphabets only"))
        {
            sent_msg("123456789");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 1");
        }
    }

    public void ft_in_ob_02_retry(String prev_msg,String prev_2_msg)
    {
        if(prev_msg.contains("enter alphabets only") && (prev_2_msg.contains("invalid response")))
        {
            sent_msg("Automatic Communication Platform for ACP");
        }
        else
        {
            Assert.fail("Validation of Free text Failed for Alphabets only input");
        }
    }

    public  void ft_in_ob_03(String prev_msg)
    {

        if(prev_msg.contains("Enter alphabets with length between 4 and 10"))
        {
            sent_msg("abcdefghijklmnopqrst");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 3");
        }
    }

    public void ft_in_ob_03_retry(String prev_msg,String prev_2_msg)
    {
        if(prev_msg.contains("Enter alphabets with length between 4 and 10") && (prev_2_msg.contains("invalid response")))
        {
            sent_msg("Whatsapp");
        }
        else
        {
            Assert.fail("Validation of Free text Failed for Alphabets only input");
        }
    }
    public  void ft_in_ob_04(String prev_msg)
    {
        if(prev_msg.contains("Alphabets start with pru"))
        {
            sent_msg("Logistics");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 4");
        }
    }

    public void ft_in_ob_04_retry(String prev_msg,String prev_2_msg)
    {
        if(prev_msg.contains("Alphabets start with pru") && (prev_2_msg.contains("invalid response")))
        {
            sent_msg("Prutech");
        }
        else
        {
            Assert.fail("Validation of Free text Failed for Alphabets starts with input");
        }
    }
    public  void ft_in_ob_05(String prev_msg)
    {
        if(prev_msg.contains("Exact alphabet - Prudent"))
        {
            sent_msg("ACP");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 5");
        }
    }
    public void ft_in_ob_05_retry(String prev_msg,String prev_2_msg)
    {
        if(prev_msg.contains("Exact alphabet - Prudent") && (prev_2_msg.contains("invalid response")))
        {
            sent_msg("Prudent");
        }
        else
        {
            Assert.fail("Validation of Free text Failed for Exact Alphabets with input");
        }
    }

    public  void ft_in_ob_06(String prev_msg)
    {
        if(prev_msg.contains("Case sensitive PRUdENT"))
        {
            sent_msg("pruDENT");
        }
        else
        {
            Assert.fail("Expected out from flow not Received from Object 6");
        }
    }

    public void ft_in_ob_06_retry(String prev_msg,String prev_2_msg)
    {
        if(prev_msg.contains("Case sensitive PRUdENT") && (prev_2_msg.contains("invalid response")))
        {
            sent_msg("PRUdENT");
        }
        else
        {
            Assert.fail("Validation of Free text Failed for Case Sensitivity Alphabets with input");
        }
    }
}
