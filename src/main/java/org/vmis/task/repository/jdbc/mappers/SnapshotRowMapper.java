package org.vmis.task.repository.jdbc.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.vmis.task.model.Snapshot;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class SnapshotRowMapper implements RowMapper<Snapshot> {

    private LocationRowMapper locationRowMapper;

    @Autowired
    public SnapshotRowMapper(LocationRowMapper locationRowMapper) {
        this.locationRowMapper = locationRowMapper;
    }

    @Override
    public Snapshot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Snapshot result = new Snapshot();
        result.setId(rs.getLong("sn_id"));
        result.setDump(rs.getString("sn_dump"));

        rs.getLong("lc_id");
        if (!rs.wasNull()) {
            result.setLastTurn(locationRowMapper.mapRow(rs, rowNum));
        }

        return result;

    }
}
