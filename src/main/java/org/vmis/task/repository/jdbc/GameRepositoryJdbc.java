package org.vmis.task.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vmis.task.model.Game;
import org.vmis.task.model.State;
import org.vmis.task.repository.GameRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Repository
public class GameRepositoryJdbc implements GameRepository {

    private static final String QUERY_FIND_ALL = "select id, state.code as state_code, title from game join state on state.id = game.state_id";
    private static final String QUERY_FIND_BY_ID = "select id, title, snapshot, state.id as state_id, state.code as state_code, state.title as state_title from game join state on state.id = game.state_id where id = :id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public GameRepositoryJdbc(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Game> findAll() {
        return jdbcTemplate.query(QUERY_FIND_ALL, new GameBriefRowMapper());
    }

    @Override
    public Game findById(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return jdbcTemplate.queryForObject(QUERY_FIND_BY_ID, parameters, new GameFullRowMapper());
    }

    private class GameBaseRowMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game result = new Game();
            result.setId(rs.getLong("id"));
            result.setTitle(rs.getString("title"));

            State state = new State();
            state.setCode(rs.getString("state_code"));
            result.setState(state);

            return result;
        }
    }

    private class GameBriefRowMapper extends GameBaseRowMapper {
    }

    private class GameFullRowMapper extends GameBaseRowMapper {

        private static final int BOARD_SIZE_WIDTH = 3;
        private static final int BOARD_SIZE_HEIGHT = 3;

        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game result = super.mapRow(rs, rowNum);
            result.setSnapshot(fromSnapshot(rs.getString("snapshot")));


            result.getState().setId(rs.getLong("state_id"));
            result.getState().setTitle(rs.getString("state_title"));

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
}
