package org.vmis.task.repository;

import org.vmis.task.model.Snapshot;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface SnapshotRepository {

    Long add(Snapshot snapshot);

    void update(Snapshot snapshot);
}
