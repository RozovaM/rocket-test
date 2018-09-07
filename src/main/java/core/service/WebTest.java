package core.service;

import core.ApiContext;
import core.CoreContext;
import core.DatabaseContext;
import core.modules.database.services.Database;
import core.modules.library.models.Config;
import core.modules.web.models.Web;
import core.modules.web.models.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@ContextConfiguration(classes = {ApiContext.class, DatabaseContext.class, CoreContext.class}, loader = AnnotationConfigContextLoader.class)
public class WebTest extends AbstractTestNGSpringContextTests {

    private WebDriver driver;

    @Autowired
    private Config config;

    @Autowired
    private ArrayList<WebDriver> webDrivers;

    @Autowired
    private Database database;

    private Integer driverNumber;

    @BeforeMethod
    public void setUp() throws Exception {
    }

    @DataProvider(name = "drivers")
    public static Object[][] createData() {
        Integer driversCount = new WebDriverFactory(new Config("config.ini")).driversCount();
        Object[][] object = new Object[driversCount][1];
        for (int i = 0; i < driversCount; i++){
            object[i][0] = i;
        }

        return object;
    }

    public Web web(Integer driverNumber) {
        this.driverNumber = driverNumber;
        return new Web(config.getPreference().node("Web").get("baseUrl", ""), webDrivers.get(driverNumber));
    }

    public Web web() {
        this.driverNumber = 0;
        return new Web(config.getPreference().node("Web").get("baseUrl", ""), webDrivers.get(0));
    }

    @AfterClass
    public void closeBrowserInstance() {
        for (WebDriver webDriver: webDrivers){
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }
}
