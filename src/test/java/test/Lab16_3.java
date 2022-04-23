package test;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.*;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lab16_3 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platforms.android);
        try{
            MobileElement navLoginBtnElem = driver.findElement(MobileBy.AccessibilityId("Forms"));
            navLoginBtnElem.click();

            // Wait until on form screen
            WebDriverWait wait = new WebDriverWait(driver,10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath("//android.widget.TextView[contains(@text,\"Form components\")]")));

            // Swipe Up/Down
            swipeUp(driver,50,50,50,10);
            swipeDown(driver,50,50,50,10);

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

    public static void swipeUp(MobileDriver driver, int xStartP, int xEndP, int yStartP, int yEndP) {
        Dimension windowSize = driver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();
        int xStartPoint = xStartP * screenWidth / 100;
        int xEndPoint = xEndP * screenWidth / 100;
        int yStartPoint = yStartP * screenHeight / 100;
        int yEndPoint = yEndP * screenHeight / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(yEndPoint,yEndPoint);
        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .longPress(startPoint)
                .moveTo(endPoint)
                .release()
                .perform();
    }

    public static void swipeDown(MobileDriver driver, int xStartP, int xEndP, int yStartP, int yEndP) {
        Dimension windowSize = driver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();
        int xStartPoint = xStartP * screenWidth / 100;
        int xEndPoint = xEndP * screenWidth / 100;
        int yStartPoint = yStartP * screenHeight / 100;
        int yEndPoint = yEndP * screenHeight / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(yEndPoint,yEndPoint);
        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .longPress(endPoint)
                .moveTo(startPoint)
                .release()
                .perform();
    }

}
