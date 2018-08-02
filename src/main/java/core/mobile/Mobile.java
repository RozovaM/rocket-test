package core.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import java.util.List;

public class Mobile {

    private AppiumDriver<MobileElement> driver;

    public Mobile(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public MobileAssertion useMobileElementsForAssertion(String xpathOfElement) throws Exception{
        List<MobileElement> elements = driver.findElementsByXPath(xpathOfElement);

        return new MobileAssertion(elements);
    }

    public TestMobileElement useElement(){
        return new TestMobileElement(driver);
    }

    public TestMobileElements useElements(){
        return new TestMobileElements(driver);
    }

    public Mobile pageElements(){
        System.out.println(driver.getPageSource());
        return this;
    }

    public Mobile swipeScreenRightLeft() throws Exception {
        driver.swipe(800,100, 1, 100, 0);
        return this;
    }

    private void swipe(int startx, int starty, int endx, int endy, int duration) { // Android ONLY!
        System.out.println("    swipe(" + startx + ", " + starty + ", " + endx + ", " + endy + ")");
        new TouchAction(driver).press(startx, starty).waitAction(0).moveTo(endx, endy).release().perform();
    }

    private void swipeIOS(int startx, int starty, int endx, int endy, int duration) {
        swipe(startx, starty, -startx + endx, -starty + endy, duration);
    }
}
