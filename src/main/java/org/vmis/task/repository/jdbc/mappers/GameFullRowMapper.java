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

    private static final int BOARD_SIZE_WIDTH = 3;
    private static final int BOARD_SIZE_HEIGHT = 3;

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
        result.setSnapshot(fromSnapshot(rs.getString("gm_snapshot")));

        return result;
    }

    private char[][] fromSnapshot(String snapshot) {
        if (snapshot.length() != BOARD_SIZE_WIDTH * BOARD_SIZE_HEIGHT) {
            throw new IllegalArgumentException("Invalid game snapshot");
        }
        char[][] result = new char[BOARD_SIZE_HEIGHT][BOARD_SIZE_WIDTH];
        char[] snapshotAsArray = snapshot.toCharArray();
        for (int y = 0; y < BOARD_SIZE_HEIGHT; y++) {
            System.arraycopy(snapshotAsArray, y * BOARD_SIZE_HEIGHT, result[y], 0, BOARD_SIZE_WIDTH);
        }
        return result;
    }
}
