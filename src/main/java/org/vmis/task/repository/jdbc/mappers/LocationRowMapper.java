package org.vmis.task.repository.jdbc.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.vmis.task.model.Location;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class LocationRowMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location result = new Location();
        result.setId(rs.getLong("lc_id"));
        result.setX(rs.getInt("lc_x"));
        result.setY(rs.getInt("lc_y"));

        return result;
    }
}
