package base;

import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class baseclass {
    public static WebDriver seleniumDriver;
    public static AppiumDriver <MobileElement> appiumDriver;

    public void AppiumbaseSetup() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();


        cap.setCapability("deviceName", "POCO C65");
        cap.setCapability("udid", "SS7L4TKFQSGEQSDI");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "14.0");
        cap.setCapability("automationName", "UIAutomator2");
        cap.setCapability("appPackage", "com.whatsapp");
        cap.setCapability("appActivity", "com.whatsapp.HomeActivity");
        cap.setCapability("fullReset",false);
        cap.setCapability("noReset",true);
        cap.setCapability("appium:forceAppLaunch", true);

        appiumDriver = new AppiumDriver<>(new URL("http://127.0.0.1:4723"), cap);

        System.out.println("APPIUM CONNECTED");
    }

    public void SeleniumbaseSetup(String url)
    {
        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions handlingSSL = new ChromeOptions();
        //Using the accept insecure cert method with true as parameter to accept the untrusted certificate
        handlingSSL.setAcceptInsecureCerts(true);
        seleniumDriver=new ChromeDriver(handlingSSL);
        //       AE.start(driver);
        seleniumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.MILLISECONDS);
        seleniumDriver.get(url);
        System.out.println("Selenium Connected");
        seleniumDriver.manage().window().maximize();
    }
}
