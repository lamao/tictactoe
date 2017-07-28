package org.vmis.task.repository.jdbc.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.vmis.task.model.Snapshot;
import org.vmis.task.repository.jdbc.converters.DumpConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class SnapshotRowMapper implements RowMapper<Snapshot> {

    private LocationRowMapper locationRowMapper;

    private DumpConverter dumpConverter;

    @Autowired
    public SnapshotRowMapper(LocationRowMapper locationRowMapper,
                             DumpConverter dumpConverter) {
        this.locationRowMapper = locationRowMapper;
        this.dumpConverter = dumpConverter;
    }

    @Override
    public Snapshot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Snapshot result = new Snapshot();
        result.setId(rs.getLong("sn_id"));

        char[][] dump = dumpConverter.toModel(rs.getString("sn_dump"));
        result.setDump(dump);

        rs.getLong("lc_id");
        if (!rs.wasNull()) {
            result.setLastTurn(locationRowMapper.mapRow(rs, rowNum));
        }

        return result;

    }
}
