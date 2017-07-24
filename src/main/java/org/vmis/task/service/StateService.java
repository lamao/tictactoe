package org.vmis.task.service;

import org.vmis.task.model.State;

import java.util.List;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface StateService {
    List<State> findAll();
}
