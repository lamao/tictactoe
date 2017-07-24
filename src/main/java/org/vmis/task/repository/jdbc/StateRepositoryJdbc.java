package org.vmis.task.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vmis.task.model.State;
import org.vmis.task.repository.StateRepository;

import java.util.List;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Repository
public class StateRepositoryJdbc implements StateRepository {

    private static final String QUERY_FIND_ALL = "select * from state";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StateRepositoryJdbc(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<State> findAll() {
        return jdbcTemplate.query(QUERY_FIND_ALL, new BeanPropertyRowMapper<>(State.class));
    }
}
