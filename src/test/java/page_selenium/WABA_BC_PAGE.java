package page_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.RobotUtil;

import java.util.ArrayList;
import java.util.List;

public class WABA_BC_PAGE {
    WebDriver driver;
    WebDriverWait wait;


    By LVS_ConfigMenu = By.xpath("(//a[contains(text(),'Configuration')])");
    By broadcast = By.xpath("(//a[contains(@href,'#/broadcast/broadcast-landing')])");
    By RCS_platform_select = By.xpath("//div[contains(text(),'RCS')]");
    By Whatsapp_platform_select = By.xpath("//div[contains(text(),'WhatsApp')]");
    By select_broadcast=By.xpath("(//select[@name=\"broadCast\"])");
    By select_service=By.xpath("//select[@name=\"id\"]");

    By select_temp_btn=By.xpath("//button[contains(text(),'Select Template')]");
    By search_templates=By.name("search");
    By select=By.xpath("//*[@id=\"templateList\"]/tbody/tr/td[4]/a");
    By broadcast_name=By.name("name");

    By broadcast_date=By.xpath("//mat-datepicker-toggle/button");
    By set_Current_date=By.xpath("//span[@class='mat-calendar-body-cell-content mat-focus-indicator mat-calendar-body-today']");
    By date_click=By.xpath("//*[@id=\"mat-datepicker-0\"]/div/mat-month-view/table/tbody/tr[4]/td[5]/button");
    By date_apply=By.xpath("(//span[contains(text(),'Apply')])");
    By broadcast_time=By.xpath("//input[@type='time']");
    By current_temp_data=By.xpath("//div[@class='clickableLink']");
    By temp_preview=By.xpath("//div[@class='previewIn']/div");
    By preview_close=By.xpath("//div[@class='mod-hed']/i");
    By response_required=By.name("responseRequired");
    By create=By.xpath("//button[contains(text(),'Create')]");
    By choose_file=By.xpath("//input[@type='file']");
    By Upload_btn=By.xpath("//button[contains(text(),'Upload')]");

    By go_to_broadcast=By.xpath("//button[contains(text(),'Go To Broadcast')]");

    By bc_name_search=By.xpath("(//input[@name='search'])[1]");
    By bc_action_type=By.name("startType");
    By action_confirm=By.xpath("(//button[contains(text(),'Yes')])[1]");

    By delivery_pingback=By.name("pingDelivery");
    By seen_pingback=By.name("pingSeen");
    By failed_pingback=By.name("pingFailed");

    public  WABA_BC_PAGE(WebDriver driver)
    {
        this.driver=driver;
    }

    public void openBroadcastPage(String platform) throws InterruptedException {
        wait=new WebDriverWait( driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LVS_ConfigMenu)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(broadcast)).click();
        Thread.sleep(2500);
        String c_url=driver.getCurrentUrl();
        String e_url="https://commpaas.com/ACP/#/broadcast/broadcast-landing";
        if(c_url.equals(e_url))
        {
            if(platform.equalsIgnoreCase("WhatsApp"))
            {
                driver.findElement(Whatsapp_platform_select).click();
            }
            else if(platform.equalsIgnoreCase("RCS"))
            {
                driver.findElement(RCS_platform_select).click();
            }
            else
            {
                System.out.println("REQUESTED PLATFORM NOT FOUND");
                Assert.fail();
            }
        }



    }

    public void select_BC_service(String service_name) throws InterruptedException {
       Thread.sleep(1000);
       wait=new WebDriverWait( driver,10);
       wait.until(ExpectedConditions.visibilityOfElementLocated(select_service));
       Select ser=new Select(driver.findElement(select_service));
       List<WebElement> options=ser.getOptions();

        List<String> optionTexts = new ArrayList<>();

        for (WebElement option : options) {
            //System.out.println(">>>>"+option.getText());
            optionTexts.add(option.getText());
        }

        if(optionTexts.contains(service_name))
        {
            ser.selectByVisibleText(service_name);
        }
        else {
            System.out.println("SERVICE NOT FOUND");
            Assert.fail();
        }
    }

    public String createNewBroadcast(String name_broadcast,String temp_name) throws InterruptedException {
        wait=new WebDriverWait( driver,10);
        Actions act=new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(select_broadcast));
        Select sel_bc=new Select(driver.findElement(select_broadcast));
        sel_bc.selectByVisibleText("New Broadcast");
        driver.findElement(select_temp_btn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(search_templates)).clear();
        driver.findElement(search_templates).sendKeys(temp_name);
        act.click(driver.findElement(By.xpath("//td[contains(text(),'"+temp_name+"')]/following-sibling::td[4]/a"))).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(broadcast_name)).clear();
        driver.findElement(broadcast_name).sendKeys(name_broadcast);
        driver.findElement(broadcast_date).click();
        act.click(driver.findElement(set_Current_date)).perform();
        driver.findElement(date_apply).click();
        driver.findElement(broadcast_time).sendKeys("0900PM");
        driver.findElement(current_temp_data).click();
        Thread.sleep(1000);
        String preview=wait.until(ExpectedConditions.visibilityOfElementLocated(temp_preview)).getText();
        act.click(driver.findElement(preview_close)).perform();
//        System.out.println(preview);
        driver.findElement(create).click();
        return preview;
    }

    public void baseUpload(String path) throws Exception {
        Actions act=new Actions(driver);
        RobotUtil util=new RobotUtil();
        act.click(driver.findElement(choose_file)).perform();
        util.fileUp_robot(path);
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(Upload_btn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(go_to_broadcast)).click();
    }

    public void start_action_bC(String bcast_name) throws Exception
    {
        wait=new WebDriverWait(driver,10);
        Thread.sleep(1500);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(bc_name_search))).clear();
        driver.findElement(bc_name_search).sendKeys(bcast_name);
        Thread.sleep(1500);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//td[contains(text(),'"+bcast_name+"')]/following-sibling::td[6]/button")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(bc_action_type));
        Select action_type=new Select(driver.findElement(bc_action_type));
        action_type.selectByVisibleText("Start");
        wait.until(ExpectedConditions.elementToBeClickable(action_confirm)).click();


    }

    public  void setPingback()
    {

    }
}
