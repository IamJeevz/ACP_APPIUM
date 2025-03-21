package test;

import base.baseclass;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_appium.flow_free_text_check;
import page_appium.waba_fetch_last_msg;
import page_selenium.WABA_BC_PAGE;
import page_selenium.login;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class flow_free_text_test extends baseclass {
    flow_free_text_check ft_check;

    @BeforeTest
    public void startUp() throws MalformedURLException {
        AppiumbaseSetup();

    }

    @Test(priority = 1)
    public void FT_flow_test() throws InterruptedException {
        ft_check=new flow_free_text_check(appiumDriver);
        ft_check.open_flow_chat();
        ft_check.sent_msg("Hi");
        ft_check.ft_ob_01(ft_check.get_waba_last_msg());
        ft_check.ft_ob_02(ft_check.get_waba_last_msg());
        ft_check.ft_ob_03(ft_check.get_waba_last_msg());
        ft_check.ft_ob_04(ft_check.get_waba_last_msg());
        ft_check.ft_ob_05(ft_check.get_waba_last_msg());
        ft_check.ft_ob_06(ft_check.get_waba_last_msg());

        String final_msg=ft_check.get_waba_last_msg();
        if(final_msg.equals("completed"))
        {
            System.out.println("FLOW EXECUTED SUCCESULLY - VALID CRITERIA OF FREE TEXT");
        }
        else
        {
            Assert.fail("FLOW EXECUTION FAILED");
        }
    }

    @Test(priority = 2)
    public void FT_invalid_test() throws InterruptedException {
//        ft_check=new flow_free_text_check(appiumDriver);
//        ft_check.open_flow_chat();
        ft_check.sent_msg("Hi");
        ft_check.ft_in_ob_01(ft_check.get_waba_last_msg());
        ft_check.ft_in_ob_02(ft_check.get_waba_last_msg());
        ft_check.ft_in_ob_02_retry(ft_check.get_waba_last_msg(),ft_check.get_waba_2last_msg());
        ft_check.ft_in_ob_03(ft_check.get_waba_last_msg());
        ft_check.ft_in_ob_03_retry(ft_check.get_waba_last_msg(),ft_check.get_waba_2last_msg());
        ft_check.ft_in_ob_04(ft_check.get_waba_last_msg());
        ft_check.ft_in_ob_04_retry(ft_check.get_waba_last_msg(),ft_check.get_waba_2last_msg());
        ft_check.ft_in_ob_05(ft_check.get_waba_last_msg());
        ft_check.ft_in_ob_05_retry(ft_check.get_waba_last_msg(),ft_check.get_waba_2last_msg());
        ft_check.ft_in_ob_06(ft_check.get_waba_last_msg());
        ft_check.ft_in_ob_06_retry(ft_check.get_waba_last_msg(),ft_check.get_waba_2last_msg());

        String final_msg=ft_check.get_waba_last_msg();
        if(final_msg.equals("completed"))
        {
            System.out.println("FLOW EXECUTED SUCCESULLY - INVALID CRITERIA OF FREE TEXT");
        }
        else
        {
            Assert.fail("FLOW EXECUTION FAILED");
        }
    }

}
