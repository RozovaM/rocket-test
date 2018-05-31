package core.modules.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.Map;

public class DatabaseDriver {

    protected DriverManagerDataSource driverManager;

    public DatabaseDriver(DriverManagerDataSource driver){
        driverManager = driver;
    }

    public DatabaseDriver has(String table, Map data){
        insert(table, data);
        return this;
    }

    private void insert(String table, Map data){
        String sql = String.format("INSERT INTO %s (%s) values ('%s')", table, String.join(", ", data.keySet()), String.join("', '", data.values()));
        System.out.println(sql);
        jdbcTemplate().update(sql);
    }

    private JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(driverManager);
    }

    public DatabaseAssert getAssert(){
        return new DatabaseAssert(driverManager);
    }
}
