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
    private static final String QUERY_FIND_BY_ID = "select id, title, state.id as state_id, state.code as state_code, state.title as state_title from game join state on state.id = game.state_id where id = :id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public GameRepositoryJdbc(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Game> findAll() {
        return jdbcTemplate.query(QUERY_FIND_ALL, new GameRowMapper());
    }

    @Override
    public Game findById(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return jdbcTemplate.queryForObject(QUERY_FIND_BY_ID, parameters, new GameRowMapper());
    }

    private class GameRowMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game result = new Game();
            result.setId(rs.getLong("id"));
            result.setTitle(rs.getString("title"));

            State state = new State();
            state.setId(rs.getLong("state_id"));
            state.setCode(rs.getString("state_code"));
            state.setTitle(rs.getString("state_title"));
            result.setState(state);

            return result;
        }
    }
}
