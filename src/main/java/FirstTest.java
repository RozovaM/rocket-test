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
        data.put("firstname", "MARIANN");
        data.put("lastname", "THANCHE");

        restApiBasicAuth
                .addHeader("Authorization", "Basic bWFyaWFubi50aGFuY2hlQHZvbHZvY2Fycy5jb206Sm9lbHNzb25za2FuMjc=")
                .addHeader("JSESSIONID","asdfsdasfaadsf")
                .addHeader("mv-districtnumber", "312")
                .sendGET("/minvolvoapi/api/v1.0/user")
                .responseCodeIs(200)
                .jsonResponseContainsData(data)
                .getJsonBody();
    }
}