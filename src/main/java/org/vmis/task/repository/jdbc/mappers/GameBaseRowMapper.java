package org.vmis.task.repository.jdbc.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.vmis.task.model.Game;
import org.vmis.task.model.State;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public class GameBaseRowMapper implements RowMapper<Game> {

    private StateRowMapper stateRowMapper;

    public GameBaseRowMapper(StateRowMapper stateRowMapper) {
        this.stateRowMapper = stateRowMapper;
    }

    @Override
    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
        Game result = new Game();
        result.setId(rs.getLong("gm_id"));
        result.setTitle(rs.getString("gm_title"));
        result.setState(stateRowMapper.mapRow(rs, rowNum));

        return result;
    }
}
