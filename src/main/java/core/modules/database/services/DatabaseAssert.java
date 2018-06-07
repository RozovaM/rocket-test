package core.modules.database.services;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;

public class DatabaseAssert {

    private JdbcTemplate jdbcTemplate;

    private LightQueryBuilder lightQueryBuilder;

    public DatabaseAssert(JdbcTemplate jdbcTemplate, LightQueryBuilder lightQueryBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.lightQueryBuilder = lightQueryBuilder;
    }

    public DatabaseAssert seeInTable(String table, HashMap data){

        String sql = "SELECT count(*) FROM " + table + lightQueryBuilder.buildWhereCondition(data);
        String count = "0";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            count = row.get("count(*)").toString();
        }

        Assert.assertTrue(Integer.parseInt(count) > 0, "There aren't such elements in database");

        return this;
    }
}
