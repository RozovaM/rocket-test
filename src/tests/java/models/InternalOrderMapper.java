package models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class InternalOrderMapper implements RowMapper<InternalOrder> {
    public InternalOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        InternalOrder order = new InternalOrder();
        order.setSessionId(rs.getString("sessionId"));
        order.setBranchId(rs.getString("branchId"));
        order.setCarId(rs.getString("carId"));
        order.setStart(rs.getString("start"));
        order.setStatus(rs.getString("status"));
        return order;
    }
}
