package org.vmis.task.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vmis.task.dto.model.GameFullDto;
import org.vmis.task.model.Game;
import org.vmis.task.repository.RepositoryConstants;

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

    private char[][] snapshotToDto(String modelSnapshot) {
        if (modelSnapshot.length() != RepositoryConstants.BOARD_SIZE_WIDTH * RepositoryConstants.BOARD_SIZE_HEIGHT) {
            throw new IllegalArgumentException("Invalid game snapshot");
        }
        char[][] result = new char[RepositoryConstants.BOARD_SIZE_HEIGHT][RepositoryConstants.BOARD_SIZE_WIDTH];
        char[] snapshotAsArray = modelSnapshot.toCharArray();
        for (int y = 0; y < RepositoryConstants.BOARD_SIZE_HEIGHT; y++) {
            System.arraycopy(snapshotAsArray, y * RepositoryConstants.BOARD_SIZE_HEIGHT, result[y], 0, RepositoryConstants.BOARD_SIZE_WIDTH);
        }
        return result;
    }
}
