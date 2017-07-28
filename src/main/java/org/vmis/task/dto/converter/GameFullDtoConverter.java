package org.vmis.task.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vmis.task.dto.model.GameFullDto;
import org.vmis.task.model.Game;
import org.vmis.task.repository.RepositoryConstants;

import java.util.Arrays;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class GameFullDtoConverter implements Converter<Game, GameFullDto> {

    private StateDtoConverter stateDtoConverter;
    private LocationDtoConverter locationDtoConverter;

    @Autowired
    public GameFullDtoConverter(StateDtoConverter stateDtoConverter, LocationDtoConverter locationDtoConverter) {
        this.stateDtoConverter = stateDtoConverter;
        this.locationDtoConverter = locationDtoConverter;
    }

    @Override
    public GameFullDto toDto(Game game) {
        GameFullDto dto = new GameFullDto();
        dto.id = game.getId();
        dto.state = stateDtoConverter.toDto(game.getState());
        dto.title = game.getTitle();
        if (game.getSnapshot().getLastTurn() != null) {
            dto.lastTurn = locationDtoConverter.toDto(game.getSnapshot().getLastTurn());
        }
        dto.snapshot = convertDump(game.getSnapshot().getDump());
        return dto;
    }

    private String[][] convertDump(char[][] dump) {
        String[][] result = new String[dump.length][];
        for (int i = 0; i < dump.length; i++) {
            result[i] = new String[dump[i].length];
            for (int k = 0; k < dump[i].length; k++) {
                result[i][k] = String.valueOf(dump[i][k]);
            }
        }
        return result;
    }
}
