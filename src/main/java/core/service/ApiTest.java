package core.service;

import core.CoreContext;
import core.models.DbPrecondition;
import core.models.api.Api;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;

@ContextConfiguration(classes = {CoreContext.class}, loader = AnnotationConfigContextLoader.class)
public class ApiTest extends AbstractTestNGSpringContextTests {
    private Api api;
    private DbPrecondition dbPrecondition;

    @BeforeClass
    public void setUp() throws Exception {
        getDbPrecondition().prepareDb();
    }

    public Api getApi() {
        return api;
    }

    @Autowired
    public ApiTest setApi(Api api) {
        this.api = api;
        return this;
    }

    public DbPrecondition getDbPrecondition() {
        return dbPrecondition;
    }

    @Autowired
    public ApiTest setDbPrecondition(DbPrecondition dbPrecondition) {
        this.dbPrecondition = dbPrecondition;
        return this;
    }
}
