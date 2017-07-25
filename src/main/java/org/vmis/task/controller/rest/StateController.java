package org.vmis.task.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vmis.task.dto.converter.StateDtoConverter;
import org.vmis.task.dto.model.StateDto;
import org.vmis.task.model.State;
import org.vmis.task.service.StateService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@RestController
@RequestMapping("/api/state")
public class StateController {

    private StateService stateService;

    private StateDtoConverter stateDtoConverter;

    @Autowired
    public StateController(StateService stateService, StateDtoConverter stateDtoConverter) {
        this.stateService = stateService;
        this.stateDtoConverter = stateDtoConverter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAllStates() {
        List<State> states = stateService.findAll();
        List<StateDto> result = states.stream().map(stateDtoConverter::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
