package api.rest.library.models;

import java.util.HashMap;

public class DataForCheck<T,B>
{
    private HashMap<T,B> data;

    public DataForCheck()
    {
        setData(new HashMap<>());
    }

    public DataForCheck add(T key, B value){
        this.data.put(key, value);
        return this;
    }

    public HashMap<T, B> getData()
    {
        return data;
    }

    private DataForCheck setData(HashMap<T, B> data)
    {
        this.data = data;
        return this;
    }
}
