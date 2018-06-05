package core.service;

import core.DatabaseContext;
import core.models.Config;
import core.models.api.DataForCheck;
import core.modules.database.DatabaseAssert;
import core.modules.database.DatabaseDriver;
import core.modules.database.Models.DbDump;
import core.modules.http.HttpAssert;
import core.modules.http.HttpDriver;
import core.service.api.EndpointService;
import core.service.api.JsonClient;
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
    protected HttpDriver httpDriver;

    @Autowired
    protected DatabaseDriver databaseDriver;

    @Autowired
    protected JsonClient jsonClient;

    @Autowired
    protected DbDump dbDump;

    @Autowired
    protected Config config;

    public DataForCheck<String, String> getDataWithStringValue()
    {
        return new DataForCheck<>();
    }

    public DataForCheck<String, Integer> getDataWithIntegerValue()
    {
        return new DataForCheck<>();
    }

    public JsonClient getJsonClient()
    {
        return jsonClient;
    }

    protected String random() {
        return new BigInteger(20, new Random()).toString(10);
    }

    // TODO: have to be moved to the base test class
    public ApiTest given(){
        return this;
    }

    // TODO: have to be moved to the base test class
    public ApiTest when(){
        return this;
    }

    // TODO: have to be moved to the base test class
    public ApiTest then(){
        return this;
    }

    // TODO: have to be moved to the base test class
    public ApiTest and(){
        return this;
    }

    // TODO: have to be moved to the base test class
    public DatabaseDriver db(){
        return databaseDriver;
    }

    // TODO: have to be moved to the base test class
    public DatabaseAssert dbAssert(){
        return databaseDriver.getAssert();
    }

    // TODO: have to be moved to the base test class
    public HttpDriver http(){
        return httpDriver;
    }

    // TODO: have to be moved to the base test class
    public HttpAssert httpAssert(){
        return httpDriver.getAssert();
    }
}