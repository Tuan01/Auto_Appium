package test.lesson18;


import driver.AppPackages;
import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;

import java.time.Duration;

public class HandleMultipleApps {
    public static void main(String[] args) {
        AppiumDriver<MobileElement>     driver = DriverFactory.getDriver(Platforms.android);

        try{
            // Login then input  > click on login btn
            // Find and click on navigation login button
            MobileElement navLoginBtnElem = driver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginBtnElem.click();

            // Fill the form
            MobileElement   usernameElem = driver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement   passwordElem = driver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement   loginBtnElem = driver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            usernameElem.sendKeys("teo@sth.com");
            passwordElem.sendKeys("12345678");
            loginBtnElem.click();

            // Put the app into background ||  similar with pressing home btn
            driver.runAppInBackground(Duration.ofSeconds(-1));
            // Turn wi-fi off || switch to another app
            driver.activateApp(AppPackages.settings);
            By connectionLabel = MobileBy.xpath(" //*[@text='Connections']");
            By wifiLabelSel = MobileBy.xpath("//*[@text='Wi-Fi']");
            By wifiStatusSel = MobileBy.id("com.android.settings:id/switch_text");

            MobileElement connectLabelElem = driver.findElement(connectionLabel);
            connectLabelElem.click();
            MobileElement wifiLabelElem = driver.findElement(wifiLabelSel);
            wifiLabelElem.click();

            //Trigger on/off wi-fi logic
            MobileElement wifiStatusElem = driver.findElement(wifiStatusSel);
            String  wifiStatusStr = wifiStatusElem.getText().trim();
            boolean isWifiOn = wifiStatusStr.equalsIgnoreCase("on");
            if (isWifiOn){
                wifiStatusElem.click();
            }
            // come back to the app  > interact with other elements
            driver.activateApp(AppPackages.wdio);
            driver.findElement(MobileBy.xpath("//*[@text='OK']")).click();

            Thread.sleep(2000);



        }catch (Exception  e){
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

}
