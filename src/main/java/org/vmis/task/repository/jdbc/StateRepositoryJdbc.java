package org.vmis.task.repository.jdbc;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vmis.task.model.State;
import org.vmis.task.repository.StateRepository;
import org.vmis.task.repository.jdbc.mappers.StateRowMapper;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Repository
public class StateRepositoryJdbc implements StateRepository {

    public static final String COLUMNS = "st_id, st_code, st_title";
    public static final String QUERY_BASE = String.format("select %s from state", COLUMNS);

    private static final String SELECT_ALL = QUERY_BASE;
    private static final String SELECT_INITIAL_STATE = String.format("%s where st_code='IN_PROGRESS'", QUERY_BASE);

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StateRepositoryJdbc(
                    NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<State> findAll() {
        return jdbcTemplate.query(SELECT_ALL, new StateRowMapper());
    }

    @Override
    public State getInitialState() {
        return jdbcTemplate.queryForObject(SELECT_INITIAL_STATE, Collections.emptyMap(), new StateRowMapper());
    }
}
