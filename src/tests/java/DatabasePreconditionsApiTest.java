import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.service.ApiTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import models.User;
import models.Car;
import models.InternalOrder;
import models.InternalOrderMapper;

public class DatabasePreconditionsApiTest extends ApiTest {
    @Test(enabled = true,
            groups = { "Dump:Basic", "Interface:RestAPI" },
            description = "Check POST /tire-shift/workshop/slot/reservation ...")
    public void bookSlot() throws JsonProcessingException {
        int userId = 10000013;
        int carId  = 12134813;
        given().db()
                .has("users", getUser(userId))
                .has("cars", getCar(carId, userId))
                .has("tireShiftInternalOrder", getOrder());
        given().http()
                .addHeader("Authorization", "Basic bWFyaWFubi50aGFuY2hlQHZvbHZvY2Fycy5jb206Sm9lbHNzb25za2FuMjc=")
                .addHeader("Content-type","application/json")
                .addHeader("JSESSIONID","asdfsdasfaadsf")
                .addHeader("mv-districtnumber", "312");
        when().http()
                .sendPOST("tire-shift/workshop/slot/reservation", getPost());
        then().httpAssert()
                //.responseCodeIs(201);
                .responseCodeIs(400);
        and().dbAssert()
                .has("tireShiftInternalOrder", new InternalOrderMapper(), getResult(carId));
    }

    private Map getUser(int id){
        Map data = new HashMap();
        data.put(User.ID, String.valueOf(id));
        data.put(User.UUID, random());
        return data;
    }

    private Map getCar(int id, int userId){
        Map data = new HashMap();
        data.put(Car.ID, String.valueOf(id));
        data.put(Car.VIN, "a93dac4d-5333-4618-b645-cbb61cba3e26");
        data.put(Car.USER_ID, String.valueOf(userId));
        data.put(Car.REG_NO, "MGT043");
        return data;
    }

    private Map getOrder(){
        Map data = new HashMap();
        data.put(InternalOrder.SESSION_ID, "66f9df99-3701-4e1f-9689-dasdada54b09b0");
        data.put(InternalOrder.STATUS, InternalOrder.CREATED);
        return data;
    }

    private String getPost() throws JsonProcessingException {
        Map data = new HashMap();
        data.put("branchId", "9");
        data.put("end", "2018-03-13T15:00:00");
        data.put("productCode", "e77087");
        data.put("registrationNumber", "MGT043");
        data.put("sessionId", "66f9df99-3701-4e1f-9689-dasdada54b09b0");
        data.put("start", "2018-03-13T14:50:00");
        return new ObjectMapper().writeValueAsString(data);
    }

    private Map getResult(int carId){
        Map data = new HashMap();
        data.put(InternalOrder.SESSION_ID, "66f9df99-3701-4e1f-9689-dasdada54b09b0");
        data.put(InternalOrder.BRANCH_ID, String.valueOf(9));
        data.put(InternalOrder.CAR_ID, String.valueOf(carId));
        data.put(InternalOrder.START, "2018-03-13T14:50:00");
        data.put(InternalOrder.STATUS, InternalOrder.CREATED);
        return data;
    }
}
