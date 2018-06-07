package core.modules.database.services;

import java.util.HashMap;
import java.util.Map;

public class LightQueryBuilder {

    public String buildWhereCondition (HashMap<String, String> data) {

        String where = " WHERE ";

        for (Map.Entry<String, String>  entry : data.entrySet()) {
            where = where + entry.getKey() + "=\"" + entry.getValue() + "\"" + " AND ";
        }

        return where.substring(0, where.length() - 5);
    }

    public String buildInsert (String table, Map data) {
        return String.format("INSERT INTO %s (%s) values ('%s')", table,
                String.join(", ", data.keySet()), String.join("', '", data.values()));
    }
}
