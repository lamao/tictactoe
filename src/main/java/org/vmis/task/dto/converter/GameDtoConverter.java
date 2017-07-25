package org.vmis.task.dto.converter;

import org.springframework.stereotype.Component;
import org.vmis.task.dto.model.GameBriefDto;
import org.vmis.task.model.Game;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class GameDtoConverter implements Converter<Game, GameBriefDto> {

    @Override
    public GameBriefDto toDto(Game game) {
        GameBriefDto dto = new GameBriefDto();
        dto.id = game.getId();
        dto.stateCode = game.getState().getCode();
        dto.title = game.getTitle();
        return dto;
    }

}
