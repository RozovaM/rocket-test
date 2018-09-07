package core.modules.web.models_new;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class WebBrowser {

    public static WebDriver initialize(String browser, DesiredCapabilities capabilities) {
        synchronized (BrowserManager.class) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeDriverManager.getInstance().setup();
                    return new ChromeDriver();
                case "firefox":
                    FirefoxDriverManager.getInstance().setup();
                    return new FirefoxDriver();
                case "ie":
                    InternetExplorerDriverManager.getInstance().setup();
                    return new InternetExplorerDriver();
                case "edge":
                    EdgeDriverManager.getInstance().setup();
                    return new EdgeDriver();
                case "safari":
                    return new SafariDriver();
                default:
                    return null;
            }
        }
    }
}
