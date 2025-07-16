package test;


import base.baseclass;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_api.v1_api_page;
import page_appium.waba_fetch_last_msg;
import page_selenium.login;
import utils.excel_read;
import utils.shell_commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;

public class api_v1 extends baseclass {


    public  String domain = "https://buzib.com/ACP";
    public  String username = "meta";
    public  String password = "Admin@1234";
    public  String waba_num="919072670612";
    public  String to_num="918547671877";
    public  String waba_fetch_contact="Gupshup ACP";
    public  String token="";
    public  String serviceKey="";
    public String excel_file_path=".\\files\\file_api_auto.xlsx";
    public  String sheet_name="Sheet1";
    public  String text_mst_content="Welcome to ACP https://openai.com/index/chatgpt/";
    waba_fetch_last_msg lm;
    shell_commands sh;
    excel_read exl = new excel_read();



    @BeforeTest
    public void startUp() throws MalformedURLException {
        AppiumbaseSetup();
    }

    @Test(priority = 0) // Donot comment this test (Using for Token calling and service key fetching)
    public void token_service() throws Exception {
        v1_api_page v1=new v1_api_page();
        token = v1.getAuthToken("https://buzib.com/ACP",username,password);
        System.out.println("Token: " + token);
        if (token != null) {
            serviceKey=v1.getServiceKeyByWaba(domain, token,waba_num);
            //System.out.println("service>>>"+serviceKey);
        }
        lm=new waba_fetch_last_msg(appiumDriver);
        lm.open_chat(waba_fetch_contact);
    }

 //   @Test(priority = 1)
    public  void audio_mp3() throws Exception {
        v1_api_page v1=new v1_api_page();
        lm.clear_chats();
        String audio_url_mp3=exl.cellValues(excel_file_path,sheet_name,1,1);
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

  //  @Test(priority = 2)
    public  void audio_amr() throws Exception {
        v1_api_page v1=new v1_api_page();
        lm=new waba_fetch_last_msg(appiumDriver);
        lm.clear_chats();
        String audio_url_amr=exl.cellValues(excel_file_path,sheet_name,2,1);
        v1.sendAudioMessage(domain,waba_num,to_num,token,serviceKey,audio_url_amr);
        Thread.sleep(1500);
        String name=lm.fetch_audio();
        if(name==null)
        {
            Assert.fail("Audio Message did not Received on Device");
        }
        else
        {
            List<String> name_split = List.of(name.split("\\."));
            if(name_split.get(1).equals("amr"))
            {
                System.out.println("Test Passed: amr File received");
            }
            else {
                Assert.fail("Received file other than amr or file not received");
            }
        }
    }

 //   @Test(priority = 3)
    public  void audio_aac() throws Exception {
        v1_api_page v1=new v1_api_page();
        lm=new waba_fetch_last_msg(appiumDriver);
        lm.clear_chats();
        String audio_url_aac=exl.cellValues(excel_file_path,sheet_name,3,1);
        v1.sendAudioMessage(domain,waba_num,to_num,token,serviceKey,audio_url_aac);
        Thread.sleep(1500);
        String name=lm.fetch_audio();
        if(name==null)
        {
            Assert.fail("Audio Message did not Received on Device");
        }
        else
        {
            List<String> name_split = List.of(name.split("\\."));
            if(name_split.get(1).equals("aac"))
            {
                System.out.println("Test Passed: aac File received");
            }
            else {
                Assert.fail("Received file other than aac or file not received");
            }
        }
    }

//    @Test(priority = 4)
    public  void audio_opus() throws Exception {
        String fileName="";
        v1_api_page v1=new v1_api_page();
        sh= new shell_commands();
        sh.delete_files();
        lm=new waba_fetch_last_msg(appiumDriver);
        lm.clear_chats();
        String audio_url_mp4=exl.cellValues(excel_file_path,sheet_name,4,1);
        v1.sendAudioMessage(domain,waba_num,to_num,token,serviceKey,audio_url_mp4);
        Thread.sleep(30000);
        String name=sh.fetch_file_name();
        System.out.println(name);



    }

//    @Test(priority = 5)
    public  void text_preview() throws Exception {
        v1_api_page v1=new v1_api_page();
        lm=new waba_fetch_last_msg(appiumDriver);
        lm.clear_chats();
        v1.sendText(domain,waba_num,to_num,token,serviceKey,text_mst_content,true);
        Thread.sleep(10000);
        String rec_text=lm.get_waba_last_msg();
//        System.out.println(rec_text);

        if(rec_text==null)
        {
            Assert.fail("Text Message did not Received on Device");
        }
        else
        {

            if(rec_text.equals(text_mst_content))
            {
                System.out.println("Test Passed: Text is received and the content is same");
            }
            else {
                Assert.fail("Text Message Received does not match");
            }
        }
    }
    @Test(priority = 6)
    public void image_jpg() throws InterruptedException {
        v1_api_page v1=new v1_api_page();
        lm=new waba_fetch_last_msg(appiumDriver);
        lm.clear_chats();
        String image_url_jpg=exl.cellValues(excel_file_path,sheet_name,5,1);
        v1.send_image(domain,waba_num,to_num,token,serviceKey,image_url_jpg,"");
    }




}
