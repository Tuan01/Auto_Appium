package test;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lab16_2 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platforms.android);

        try{
            // Get mobile window size
            Dimension windowSize = driver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate Touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 0 * screenHeight / 100;
            int yEndPoint = 50 * screenHeight / 100;

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(yEndPoint,yEndPoint);

            // Using TouchAction to swipe form right to left
            TouchAction  touchAction = new TouchAction(driver);
                touchAction
                        .press(startPoint)
                        .moveTo(endPoint)
                        .release()
                        .perform();
            MobileElement navLoginBtnElem = driver.findElement(MobileBy.id("android:id/notification_header"));
            navLoginBtnElem.click();
            WebDriverWait wait = new WebDriverWait(driver,10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("android:id/notification_main_column")));
            Map<String,String> notification = new HashMap<>();
            List<MobileElement> notificationElems = driver.findElements(MobileBy.id("android:id/notification_main_column"));
            for (MobileElement notificationElem : notificationElems) {
                MobileElement titleElem = notificationElem.findElement(MobileBy.id("android:id/title"));
                String titleText = titleElem.getText();
                MobileElement contentElem = notificationElem.findElement(MobileBy.id("android:id/text"));
                String contentText = contentElem.getText();

                notification.put(titleText, contentText);

            }
                if(notification.keySet().isEmpty()){
                    throw new RuntimeException("[ERR] there is no notification to test");
                }else{
                    for (String title : notification.keySet()) {
                        System.out.println("Title: " + title);
                        System.out.println("Content: " +notification.get(title));
                    }
                }

                //
                 touchAction
                    .longPress(endPoint)
                    .moveTo(startPoint)
                    .release()
                    .perform();

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }
}
