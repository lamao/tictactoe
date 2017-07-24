package org.vmis.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vmis.task.model.State;
import org.vmis.task.repository.StateRepository;
import org.vmis.task.service.StateService;

import java.util.List;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Service
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    @Autowired
    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }
}
