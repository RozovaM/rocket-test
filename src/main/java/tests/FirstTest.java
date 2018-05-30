package tests;

import core.service.ApiTest;
import org.testng.annotations.Test;
import java.util.HashMap;

public class FirstTest extends ApiTest
{
    @Test(enabled = true,
            groups = { "Dump:Basic", "Interface:CoreAPI" },
            description = "Check GET /tire-shift/dealers/:dealer/tire ...")
    public void getTireList()
    {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Hakka Blue 2 XL");
        data.put("dimensions", "195/65R15 95V");
        data.put("manufacturer", "Nokian");
        restApiBasicAuth
                .addHeader("Authorization", "Basic bWFyaWFubi50aGFuY2hlQHZvbHZvY2Fycy5jb206Sm9lbHNzb25za2FuMjc=")
                .addHeader("JSESSIONID","asdfsdasfaadsf")
                .addHeader("mv-districtnumber", "312")
                .sendGET("tire-shift/dealers/16/tire")
                .responseCodeIs(200)
                .jsonResponseContainsField("items")
                .jsonResponseContainsDataInCollection(data)
                .getJsonBody();
    }
}