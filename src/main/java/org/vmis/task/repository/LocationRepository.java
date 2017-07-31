package org.vmis.task.repository;

import org.vmis.task.model.Location;

import java.util.List;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface LocationRepository {
    Long add(Location location);

    List<Location> getTurnsByGameId(Long gameId);
}
