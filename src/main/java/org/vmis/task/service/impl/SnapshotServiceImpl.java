package org.vmis.task.service.impl;

import org.springframework.stereotype.Service;
import org.vmis.task.repository.RepositoryConstants;
import org.vmis.task.service.SnapshotService;

import java.util.Arrays;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Service
public class SnapshotServiceImpl implements SnapshotService {
    @Override
    public String createInitialSnapshot() {
        char[] snapshot = new char[RepositoryConstants.BOARD_SIZE_HEIGHT * RepositoryConstants.BOARD_SIZE_WIDTH];
        Arrays.fill(snapshot, RepositoryConstants.BOARD_EMPTY_CELL);
        return String.valueOf(snapshot);
    }
}
