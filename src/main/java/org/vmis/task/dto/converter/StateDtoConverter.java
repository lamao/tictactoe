package org.vmis.task.dto.converter;

import org.springframework.stereotype.Component;
import org.vmis.task.dto.model.StateDto;
import org.vmis.task.model.State;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class StateDtoConverter implements Converter<State, StateDto> {
    @Override
    public StateDto toDto(State state) {
        StateDto dto = new StateDto();
        dto.code = state.getCode();
        dto.title = state.getTitle();
        return dto;
    }
}
