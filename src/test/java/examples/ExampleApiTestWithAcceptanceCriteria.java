package examples;

import core.service.ApiTest;
import org.testng.annotations.Test;

public class ExampleApiTestWithAcceptanceCriteria extends ApiTest {
    @Test(enabled = true,
            groups = { "Dump:Basic", "Interface:RestAPI" },
            description = "Check POST /tire-shift/workshop/slot/reservation ...")
    public void bookSlot() {

        int userId = 10000013;
        int carId  = 12134813;

        ac().given().db()
                .haveInTable("users", getDataWithStringValue()
                        .add("id", String.valueOf(userId)).add("uuid", random()).getData())
                .haveInTable("cars", getDataWithStringValue()
                        .add("id", String.valueOf(carId)).add("vin", "a93dac4d-5333-4618-b645-cbb61cba3e26")
                        .add("userId", String.valueOf(userId)).add("regNo", "MGT043").getData())
                .haveInTable("tireShiftInternalOrder", getDataWithStringValue()
                        .add("sessionId", "66f9df99-3701-4e1f-9689-dasdada54b09b0").add("status", "created").getData());

        ac().when().rest()
                .addHeader("Authorization", "Basic bWFyaWFubi50aGFuY2hlQHZvbHZvY2Fycy5jb206Sm9lbHNzb25za2FuMjc=")
                .addHeader("Content-type","application/json")
                .addHeader("JSESSIONID","asdfsdasfaadsf")
                .addHeader("mv-districtnumber", "312")
                .sendPOST("tire-shift/workshop/slot/reservation", getDataWithStringValue().add("branchId", "9")
                        .add("end", "2018-03-13T15:00:00")
                        .add("productCode", "e77087")
                        .add("registrationNumber", "MGT043")
                        .add("sessionId", "66f9df99-3701-4e1f-9689-dasdada54b09b0")
                        .add("start", "2018-03-13T14:50:00").toString())
                .ac().then().responseCodeIs(201);

        ac().then().db().assertion()
                .seeInTable("tireShiftInternalOrder",
                        getDataWithStringValue().add("carId", "12134813").add("start", "2018-03-13 14:50:00").getData());
    }
}
