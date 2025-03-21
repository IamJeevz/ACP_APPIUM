package test;

import base.baseclass;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_appium.waba_fetch_last_msg;
import page_selenium.WABA_BC_PAGE;
import page_selenium.login;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class broadcast_test_waba extends baseclass {

    login le;
    WABA_BC_PAGE bc;
    waba_fetch_last_msg wa_msg;
    public  String b_cast_name="auto_"+new SimpleDateFormat("ddMMyy_HHmmss").format(new Date());
    @BeforeTest
    public void startUp() throws MalformedURLException {
        SeleniumbaseSetup("https://commpaas.com/ACP");
        AppiumbaseSetup();
        le=new login(seleniumDriver);
        le.loginCredentials("JEEVAN","Admin@123");
        le.loginClick();

    }

    @Test
    public void BroadCast_WABA() throws Exception {

        wa_msg=new waba_fetch_last_msg(appiumDriver);
        bc=new WABA_BC_PAGE(seleniumDriver);

        bc.openBroadcastPage("WhatsApp");
        bc.select_BC_service("claro");
        String preview_data=bc.createNewBroadcast(b_cast_name,"templatecricket");
        Thread.sleep(2500);
        bc.baseUpload(System.getProperty("user.dir") + "\\files\\waba_base.xlsx");
        bc.start_action_bC(b_cast_name);
        Thread.sleep(5000);
        String waba_msg=wa_msg.get_waba_last_msg();
//
//        System.out.println("preview_data>>>"+preview_data);
//        System.out.println("waba_msg>>>"+waba_msg);
        if(preview_data.equals(waba_msg))
        {
            System.out.println("Test Case passed: Broadcast Received on WhatsApp succesfully");
        }
        else
        {
            System.out.println("Test Case Failed");
            Assert.fail();
        }

    }

}
