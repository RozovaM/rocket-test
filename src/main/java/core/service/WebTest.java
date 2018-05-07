package core.service;

import com.codeborne.selenide.Screenshots;
import core.CoreContext;
import core.models.DbPrecondition;
import core.models.web.Web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

@ContextConfiguration(classes = {CoreContext.class}, loader = AnnotationConfigContextLoader.class)
public class WebTest extends AbstractTestNGSpringContextTests {
    private Web web;
    private DbPrecondition dbPrecondition;

    @BeforeClass
    public void setUp() throws Exception {
        getDbPrecondition().prepareDb();
    }

    @AfterMethod
    public void closeBrowserInstance() throws IOException {
        //SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        saveScreenshot();
        com.codeborne.selenide.Selenide.close();
    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] saveScreenshot() throws IOException {
        File picture = Screenshots.takeScreenShotAsFile();
        return com.google.common.io.Files.toByteArray(picture);
    }

    public Web getWeb() {
        return web;
    }

    @Autowired
    public WebTest setWeb(Web web) {
        this.web = web;
        return this;
    }

    public DbPrecondition getDbPrecondition() {
        return dbPrecondition;
    }

    @Autowired
    public WebTest setDbPrecondition(DbPrecondition dbPrecondition) {
        this.dbPrecondition = dbPrecondition;
        return this;
    }
}
