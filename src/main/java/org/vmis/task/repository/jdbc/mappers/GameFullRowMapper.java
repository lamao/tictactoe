package org.vmis.task.repository.jdbc.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vmis.task.model.Game;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class GameFullRowMapper extends GameBaseRowMapper {

    private LocationRowMapper locationRowMapper;

    @Autowired
    public GameFullRowMapper(StateRowMapper stateRowMapper, LocationRowMapper locationRowMapper) {
        super(stateRowMapper);
        this.locationRowMapper = locationRowMapper;
    }

    @Override
    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
        Game result = super.mapRow(rs, rowNum);

        rs.getLong("lc_id");
        if (!rs.wasNull()) {
            result.setLastTurn(locationRowMapper.mapRow(rs, rowNum));
        }
        result.setSnapshot(rs.getString("gm_snapshot"));

        return result;
    }
}
