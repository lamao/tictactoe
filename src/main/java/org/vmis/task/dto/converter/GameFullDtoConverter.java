package org.vmis.task.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vmis.task.dto.model.GameBriefDto;
import org.vmis.task.dto.model.GameFullDto;
import org.vmis.task.model.Game;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class GameFullDtoConverter implements Converter<Game, GameFullDto> {

    private StateDtoConverter stateDtoConverter;

    @Autowired
    public GameFullDtoConverter(StateDtoConverter stateDtoConverter) {
        this.stateDtoConverter = stateDtoConverter;
    }

    @Override
    public GameFullDto toDto(Game game) {
        GameFullDto dto = new GameFullDto();
        dto.id = game.getId();
        dto.state = stateDtoConverter.toDto(game.getState());
        dto.title = game.getTitle();
        return dto;
    }
}
