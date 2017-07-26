package org.vmis.task.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vmis.task.dto.model.GameFullDto;
import org.vmis.task.model.Game;

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
        if (game.getLastTurn() != null) {
            dto.lastTurn = locationDtoConverter.toDto(game.getLastTurn());
        }
        dto.snapshot = snapshotToDto(game.getSnapshot());
        return dto;
    }

    private char[][] snapshotToDto(char[][] modelSnapshot) {
        char[][] snapshot = new char[modelSnapshot.length][];
        for (int y = 0; y < modelSnapshot.length; y++) {
            snapshot[y] = new char[modelSnapshot[y].length];
            System.arraycopy(modelSnapshot[y], 0, snapshot[y], 0, modelSnapshot[y].length);
        }
        return snapshot;
    }
}
