package org.vmis.task.repository;

import org.vmis.task.model.State;

import java.util.List;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface StateRepository {

    List<State> findAll();

    State getInitialState();

    State findByCode(String code);
}
