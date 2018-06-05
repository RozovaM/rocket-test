package models;

public class InternalOrder {
    public static final String SESSION_ID = "sessionId";
    public static final String BRANCH_ID = "branchId";
    public static final String CAR_ID = "carId";
    public static final String START = "start";
    public static final String STATUS = "status";

    public static final String CREATED = "created";

    private String sessionId;
    private int branchId;
    private int carId;
    private String start;
    private String status;

    public void setSessionId(String id){
        sessionId = id;
    }

    public void setBranchId(int id){
        branchId = id;
    }

    public void setBranchId(String id){
        if (id != null) {
            setBranchId(Integer.parseInt(id));
        }
    }

    public void setCarId(int id){
        carId = id;
    }

    public void setCarId(String id){
        setCarId(Integer.parseInt(id));
    }

    public void setStart(String date){
        start = date;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
