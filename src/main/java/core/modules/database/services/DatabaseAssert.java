package core.modules.database.services;

import core.modules.library.models.Verbose;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;

public class DatabaseAssert {

    private JdbcTemplate jdbcTemplate;

    private LightQueryBuilder lightQueryBuilder;

    private Verbose verbose;

    public DatabaseAssert(JdbcTemplate jdbcTemplate, LightQueryBuilder lightQueryBuilder, Verbose verbose) {
        this.jdbcTemplate = jdbcTemplate;
        this.lightQueryBuilder = lightQueryBuilder;
        this.verbose = verbose;
    }

    public DatabaseAssert seeInTable(String table, HashMap data){

        String sql = "SELECT count(*) FROM " + table + lightQueryBuilder.buildWhereCondition(data);
        String count = "0";

        verbose.testInfo("Find condition in table", sql);

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            count = row.get("count(*)").toString();
        }

        Assert.assertTrue(Integer.parseInt(count) > 0, "There aren't such elements in database");

        return this;
    }
}
