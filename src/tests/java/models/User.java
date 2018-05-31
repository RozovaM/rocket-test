package models;

public class User {
    public static final String ID = "id";
    public static final String UUID = "uuid";

    private int id;
    private int uuid;

    public void setId(int id){
        this.id = id;
    }

    public void setId(String id){
        setId(Integer.parseInt(id));
    }

    public void setUuid(int uuid){
        this.uuid = uuid;
    }

    public void setUuid(String uuid){
        setUuid(Integer.parseInt(uuid));
    }
}
