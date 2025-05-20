package test;


import base.baseclass;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_api.v1_api_page;
import page_appium.waba_fetch_last_msg;
import page_selenium.login;
import utils.shell_commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;

public class api_v1 extends baseclass {


    public String domain = "https://buzib.com/ACP";
    public  String username = "jeevan";
    public  String password = "Admin@1234";
    public  String waba_num="5116045163";
    public  String to_num="918547671877";
    public  String waba_fetch_last_msg="360";
    public  String serviceKey="";
    public  String audio_url_mp3="https://prutech.org/MediaServer/api/Media/Data/f39ae9ff3a/2mb1.mp3";
    public  String audio_url_mp4="https://tmpfiles.org/dl/28182452/3mb1.opus";
    waba_fetch_last_msg lm;
    shell_commands sh;
    @BeforeTest
    public void startUp() throws MalformedURLException {
        AppiumbaseSetup();
    }

//    @Test(priority = 1)
    public  void audio_mp3() throws Exception {
        v1_api_page v1=new v1_api_page();
        lm=new waba_fetch_last_msg(appiumDriver);
        String token = v1.getAuthToken("https://buzib.com/ACP",username,password);
        System.out.println("Token: " + token);
        if (token != null) {
            serviceKey=v1.getServiceKeyByWaba(domain, token,waba_num);

        }
        //System.out.println("service>>>"+serviceKey);
        lm.open_chat(waba_fetch_last_msg);
        lm.clear_chats();
        v1.sendAudioMessage(domain,waba_num,to_num,token,serviceKey,audio_url_mp3);
        Thread.sleep(1500);
        String name=lm.fetch_audio();
        if(name==null)
        {
            Assert.fail("Audio Message did not Received on Device");
        }
        else
        {
            List<String> name_split = List.of(name.split("\\."));
            if(name_split.get(1).equals("mp3"))
            {
                System.out.println("Test Passed: mp3 File received");
            }
            else {
                Assert.fail("Received file other than mp3 or file not received");
            }
        }
    }

    @Test(priority = 2)
    public  void audio_opus() throws Exception {
        String fileName="";
        v1_api_page v1=new v1_api_page();
        sh= new shell_commands();
        sh.delete_files();
        lm=new waba_fetch_last_msg(appiumDriver);
        String token = v1.getAuthToken("https://buzib.com/ACP",username,password);
        System.out.println("Token: " + token);
        if (token != null) {
            serviceKey=v1.getServiceKeyByWaba(domain, token,waba_num);

        }
        //System.out.println("service>>>"+serviceKey);
        lm.open_chat(waba_fetch_last_msg);
//        lm.clear_chats();
        v1.sendAudioMessage(domain,waba_num,to_num,token,serviceKey,audio_url_mp4);
        Thread.sleep(30000);
        String name=sh.fetch_file_name();
        System.out.println(name);



    }

}
