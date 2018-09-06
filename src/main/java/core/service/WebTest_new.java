package core.service;

import core.ApiContext;
import core.CoreContext;
import core.DatabaseContext;
import core.modules.database.services.DbPrecondition;
import core.modules.library.models.Config;
import core.modules.web.models_new.BrowserProvider;
import core.modules.web.models_new.Web;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

@ContextConfiguration(classes = {ApiContext.class, DatabaseContext.class, CoreContext.class}, loader = AnnotationConfigContextLoader.class)
public class WebTest_new extends AbstractTestNGSpringContextTests {

    private DbPrecondition dbPrecondition;
    private Web web;
    private WebDriver driver;
    private Config config;

    @BeforeMethod
    public void setUp() throws Exception {
        //getDbPrecondition().prepareDb();
        config = new Config("config.ini");
        driver = BrowserProvider.createDriver(config.getPreference().node("Web").get("webBrowser", ""));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public Web getWeb() throws Exception{
        if (web != null){
            return web;
        }
        config = new Config("config.ini");
        web = new Web(config.getPreference().node("Web").get("baseUrl", ""), getBrowserDriver());
        return web;
    }

    @AfterMethod
    public void closeBrowserInstance() throws Exception {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getBrowserDriver () {
        return driver;
    }

    public DbPrecondition getDbPrecondition() {
        return dbPrecondition;
    }

    @Autowired
    public WebTest_new setDbPrecondition(DbPrecondition dbPrecondition) {
        this.dbPrecondition = dbPrecondition;
        return this;
    }
}
