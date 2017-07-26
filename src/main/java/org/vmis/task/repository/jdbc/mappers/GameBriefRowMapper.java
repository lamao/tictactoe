package org.vmis.task.repository.jdbc.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class GameBriefRowMapper extends GameBaseRowMapper {

    @Autowired
    public GameBriefRowMapper(StateRowMapper stateRowMapper) {
        super(stateRowMapper);
    }
}
