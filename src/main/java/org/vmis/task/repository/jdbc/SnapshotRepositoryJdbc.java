package org.vmis.task.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.vmis.task.model.Snapshot;
import org.vmis.task.repository.SnapshotRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class SnapshotRepositoryJdbc implements SnapshotRepository {
    public static final String COLUMNS = "sn_id, sn_last_turn_id, sn_dump";
//    public static final String QUERY_BASE = String.format("select %s from lc_location", COLUMNS);

    public static final String INSERT_ADD = "insert into snapshot (sn_dump) values (:dump)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public SnapshotRepositoryJdbc(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(Snapshot snapshot) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dump", snapshot.getDump());

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_ADD, new MapSqlParameterSource(parameters), keyHolder);
        return keyHolder.getKey().longValue();
    }
}
