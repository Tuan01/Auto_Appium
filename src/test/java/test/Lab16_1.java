package test;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Lab16_1 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platforms.android);

        try{
            MobileElement navLoginBtnElem = driver.findElement(MobileBy.AccessibilityId("Forms"));
            navLoginBtnElem.click();

            // Wait until on form screen
            WebDriverWait wait = new WebDriverWait(driver,10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath("//android.widget.TextView[contains(@text,\"Form components\")]")));
            driver.findElement(MobileBy.AccessibilityId("text-input")).sendKeys("Demo");
            String textTyped = driver.findElement(MobileBy.AccessibilityId("input-text-result")).getText();
            System.out.println("The text typed is" +textTyped);
            MobileElement  switchRadio = driver.findElement(MobileBy.AccessibilityId("switch"));
            switchRadio.click();
            MobileElement textSwitched =
                    driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Click to turn the switch OFF\")"));
            Assert.assertTrue(textSwitched.isDisplayed());

            // Get mobile window size
            Dimension windowSize = driver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate Touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 50 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(yEndPoint,yEndPoint);

            // Using TouchAction To Swipe
            TouchAction  touchAction = new TouchAction(driver);
            touchAction
                    .longPress(startPoint)
                    .moveTo(endPoint)
                    .release()
                    .perform();

            // Select Dropdown
            MobileElement  selectDropDown = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Select an item...\")"));
            selectDropDown.click();
            MobileElement firstOption = driver.findElementByXPath("//*[@text='webdriver.io is awesome']");
            firstOption.click();

            // Click on active button
            driver.findElement(MobileBy.AccessibilityId("button-Active")).click();

            // A popup is shown normally
            MobileElement  popup = driver.findElement(MobileBy.id("android:id/parentPanel"));
            popup.isDisplayed();
            MobileElement acceptValuePopup = driver.findElement(MobileBy.id("android:id/button1"));
            acceptValuePopup.click();

            // Sleep
            Thread.sleep(2000);

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

}
