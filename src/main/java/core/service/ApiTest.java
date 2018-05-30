package core.service;

import core.models.Config;
import api.rest.library.models.DataForCheck;
import api.rest.library.services.EndpointService;
import api.rest.library.services.JsonClient;
import core.ApiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.math.BigInteger;
import java.util.Random;


@ContextConfiguration(classes = {ApiContext.class}, loader = AnnotationConfigContextLoader.class)
public class ApiTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    protected EndpointService restApiBasicAuth;

    @Autowired
    protected JsonClient jsonClient;

    public ApiTest()
    {
        Config config = new Config("config.ini");
    }

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
}