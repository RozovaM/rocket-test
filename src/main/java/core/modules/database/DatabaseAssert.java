package core.modules.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;

public class DatabaseAssert {

    protected DriverManagerDataSource driverManager;

    public DatabaseAssert(DriverManagerDataSource driver){
        driverManager = driver;
    }

    public DatabaseAssert has(String table, RowMapper model, Map data){
        List items = select(table, model, data);
        // TODO: implement data comparison
        return this;
    }

    private List select(String table, RowMapper model, Map data){
        String sql = String.format("SELECT %s FROM %s", String.join(", ", data.keySet()), table);
        System.out.println(sql);
        return jdbcTemplate().query(sql, model);
    }

    private JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(driverManager);
    }
}
