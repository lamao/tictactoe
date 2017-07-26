package org.vmis.task.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.vmis.task.model.Location;
import org.vmis.task.repository.LocationRepository;
import org.vmis.task.repository.jdbc.mappers.LocationRowMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public class LocationRepositoryJdbc implements LocationRepository {

    public static final String COLUMNS = "lc_id, lc_x, lc_y";
    public static final String QUERY_BASE = String.format("select %s from lc_location", COLUMNS);
}
