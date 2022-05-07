package test;


import context.Contexts;
import context.WaitMoreThanOneContext;
import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HybridContext {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platforms.android);
        try {
            By webviewNavBtnSel = MobileBy.AccessibilityId("Webview");
            MobileElement webviewNavBtnElem = appiumDriver.findElement(webviewNavBtnSel);
            webviewNavBtnElem.click();

            // Wait until we have more than one context
            WebDriverWait wait = new WebDriverWait(appiumDriver,15L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));

            // Switch to web view context
            appiumDriver.context(Contexts.WEB_VIEW);

            // Interact on webview elements
            WebElement  navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();

            List<MobileElement> menuItemElems = appiumDriver.findElementsByCssSelector(".menu__list li a");
            List<MenuItemData>  menuItemDataList = new ArrayList<>();
            Map<String, String> menuItemDataMap = new HashMap<>();
            if(menuItemElems.isEmpty()){
                throw new RuntimeException("[ERR] There is no list item!!");
            }

            for (MobileElement menuItemElem : menuItemElems) {
                String itemText = menuItemElem.getText();
                String itemHref = menuItemElem.getAttribute("href");
                if(itemText.isEmpty()){
                    menuItemDataList.add(new MenuItemData("Github", itemHref));
                    menuItemDataMap.put("Github",itemHref);
                }else{
                    menuItemDataList.add(new MenuItemData(itemText, itemHref));
                    menuItemDataMap.put(itemText, itemHref);
                }
            }
            for (MenuItemData menuItemData : menuItemDataList) {
                System.out.println(menuItemData.getName() + ":" + menuItemData.getHref());
            }

            System.out.println("======================");

            for (String key : menuItemDataMap.keySet()) {
                System.out.println(key + ":" + menuItemDataMap.get(key));
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            appiumDriver.quit();
        }
    }

    public static class MenuItemData{
        private String name;
        private String href;

        public MenuItemData(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName(){
            return name;
        }
        public String getHref(){
            return href;
        }
    }


}
