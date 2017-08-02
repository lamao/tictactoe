package org.vmis.task.service;

import org.vmis.task.model.Location;

import java.util.List;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface LocationService {
    List<Location> getTurnHistory(Long gameId);
}
