package test;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;

public class TakingScreenshot {
    public static void main(String[] args) {

        // Add one more dependence: commons-io

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platforms.android);
        try{
            MobileElement   navLoginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginBtnElem.click();

            // whole screen
            File base64ScreenshotData = appiumDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("HomeScreen.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

            // An area
            MobileElement loginFormElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login-screen"));
            File base64LoginFormData = loginFormElem.getScreenshotAs(OutputType.FILE);
            String loginFormFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginForm.png");
            FileUtils.copyFile(base64LoginFormData, new File(loginFormFileLocation));

            // An element
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));
            File base64LoginBtnData = loginBtnElem.getScreenshotAs(OutputType.FILE);
            String loginBtnFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("Loginbtn.png");
            FileUtils.copyFile(base64LoginBtnData, new File(loginBtnFileLocation));

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }
}
