package org.vmis.task.service;

import org.vmis.task.model.Snapshot;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface SnapshotService {
    Snapshot createInitialSnapshot();
}
