package org.vmis.task.repository.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.vmis.task.model.Game;
import org.vmis.task.repository.GameRepository;
import org.vmis.task.repository.jdbc.mappers.GameBriefRowMapper;
import org.vmis.task.repository.jdbc.mappers.GameFullRowMapper;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Repository
public class GameRepositoryJdbc implements GameRepository {

    public static final String COLUMNS_BASE = "gm_id, gm_title";
    public static final String COLUMNS_BRIEF = COLUMNS_BASE;
    public static final String COLUMNS_FULL = String.format("%s, gm_snapshot", COLUMNS_BASE);

    public static final String QUERY_BRIEF = String.format(
                    "select %s, %s from game join state on st_id = gm_state_id", COLUMNS_BRIEF,
                    StateRepositoryJdbc.COLUMNS);
    public static final String QUERY_FULL = String
                    .format("select %s, %s, %s from game join state on st_id = gm_state_id left join location on lc_id = gm_last_turn_id",
                                    COLUMNS_FULL, StateRepositoryJdbc.COLUMNS,
                                    LocationRepositoryJdbc.COLUMNS);

    private static final String SELECT_ALL = QUERY_BRIEF;
    private static final String SELECT_BY_ID = String.format("%s where gm_id = :id", QUERY_FULL);
    private static final String INSERT_ADD = "insert into game (gm_title, gm_state_id, gm_snapshot) values (:title, :state_id, :snapshot)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    private GameBriefRowMapper gameBriefRowMapper;
    private GameFullRowMapper gameFullRowMapper;

    @Autowired
    public GameRepositoryJdbc(
                    NamedParameterJdbcTemplate jdbcTemplate,
                    GameBriefRowMapper gameBriefRowMapper,
                    GameFullRowMapper gameFullRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.gameBriefRowMapper = gameBriefRowMapper;
        this.gameFullRowMapper = gameFullRowMapper;
    }

    @Override
    public List<Game> findAll() {
        return jdbcTemplate.query(SELECT_ALL, gameBriefRowMapper);
    }

    @Override
    public Game findById(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return jdbcTemplate.queryForObject(SELECT_BY_ID, parameters, gameFullRowMapper);
    }

    @Override
    public long add(Game game) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", game.getTitle());
        parameters.put("state_id", game.getState().getId());
        parameters.put("snapshot", game.getSnapshot());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_ADD, new MapSqlParameterSource(parameters), keyHolder);
        return keyHolder.getKey().longValue();
    }
}
