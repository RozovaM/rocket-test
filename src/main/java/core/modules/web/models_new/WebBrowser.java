package core.modules.web.models_new;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

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
                default:
                    return null;
            }
        }
    }
}
