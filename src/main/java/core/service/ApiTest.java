package core.service;

import core.CoreContext;
import core.DatabaseContext;
import core.modules.bdd.models.AcceptanceCriteria;
import core.modules.database.services.Database;
import core.modules.library.models.Verbose;
import core.modules.rest.models.DataForCheck;
import core.modules.rest.services.EndpointService;
import core.modules.rest.services.JsonClient;
import core.ApiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.math.BigInteger;
import java.util.Random;

@ContextConfiguration(classes = {ApiContext.class, DatabaseContext.class, CoreContext.class}, loader = AnnotationConfigContextLoader.class)
public class ApiTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    private EndpointService restApiBasicAuth;

    @Autowired
    private EndpointService restApiOAuth2;

    @Autowired
    private Database database;

    @Autowired
    private JsonClient jsonClient;

    @Autowired
    private Verbose verbose;

    @AfterMethod
    protected void testInformation(ITestResult result) {
        verbose.testReport(result);
    }

    public DataForCheck getDataWithStringValue()
    {
        return new DataForCheck();
    }

    public JsonClient getJsonClient()
    {
        return jsonClient;
    }

    protected String random() {
        return new BigInteger(20, new Random()).toString(10);
    }

    public AcceptanceCriteria<ApiTest> ac() {
        return new AcceptanceCriteria<>(this);
    }

    public Database db(){
        return database;
    }

    public EndpointService rest() {
        return restApiBasicAuth;
    }

    public EndpointService restWithOAuth() {
        return restApiOAuth2;
    }
}