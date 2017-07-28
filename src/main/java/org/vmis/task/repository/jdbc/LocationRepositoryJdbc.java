package org.vmis.task.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.vmis.task.model.Location;
import org.vmis.task.repository.LocationRepository;
import org.vmis.task.repository.jdbc.mappers.LocationRowMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Repository
public class LocationRepositoryJdbc implements LocationRepository {

    public static final String COLUMNS = "lc_id, lc_x, lc_y";
    public static final String QUERY_BASE = String.format("select %s from lc_location", COLUMNS);

    public  static final String INSERT_ADD = "insert into location (lc_x, lc_y) values (:x, :y)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public LocationRepositoryJdbc(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(Location location) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("x", location.getX());
        parameters.put("y", location.getY());

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_ADD, new MapSqlParameterSource(parameters), keyHolder);
        return keyHolder.getKey().longValue();
    }
}
