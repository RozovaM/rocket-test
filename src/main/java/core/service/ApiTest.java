package core.service;

import core.DatabaseContext;
import core.modules.library.models.Config;
import core.modules.bdd.models.AcceptanceCriteria;
import core.modules.database.services.Database;
import core.modules.database.models.DbDump;
import core.modules.rest.models.DataForCheck;
import core.modules.rest.services.EndpointService;
import core.modules.rest.services.JsonClient;
import core.ApiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


import java.math.BigInteger;
import java.util.Random;

@ContextConfiguration(classes = {ApiContext.class, DatabaseContext.class}, loader = AnnotationConfigContextLoader.class)
public class ApiTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    protected EndpointService restApiBasicAuth;

    @Autowired
    protected Database database;

    @Autowired
    protected JsonClient jsonClient;

    @Autowired
    protected DbDump dbDump;

    @Autowired
    protected Config config;

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

    public EndpointService rest(){
        return restApiBasicAuth;
    }
}