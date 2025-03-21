package page_selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class login {
    WebDriver driver;
    WebDriverWait wait;

    By LVS_user_name= By.xpath("//*[@id=\"input-username\"]");
    By LVS_pass_word = By.xpath("//*[@id=\"input-password\"]");
    By LVS_button=By.xpath("//*[@id=\"body\"]/app-dashboard/div/main/div/div/div/div/div[2]/div/form/div[3]/div[1]/button");

    public login(WebDriver driver)
    {
        this.driver=driver;
    }


    public void loginCredentials(String username,String password) {


        wait=new WebDriverWait(driver, (15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LVS_user_name));
        driver.findElement(LVS_user_name).sendKeys(username);
        driver.findElement(LVS_pass_word).sendKeys(password);
    }
    public void loginClick()
    {

        driver.findElement(LVS_button).click();
    }
}
