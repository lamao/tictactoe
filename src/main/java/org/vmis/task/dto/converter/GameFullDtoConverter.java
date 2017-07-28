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
        if (game.getSnapshot().getLastTurn() != null) {
            dto.lastTurn = locationDtoConverter.toDto(game.getSnapshot().getLastTurn());
        }
        dto.snapshot = parseDump(game.getSnapshot().getDump());
        return dto;
    }

    private String[][] parseDump(String modelSnapshot) {
        if (modelSnapshot.length() != RepositoryConstants.BOARD_SIZE_WIDTH * RepositoryConstants.BOARD_SIZE_HEIGHT) {
            throw new IllegalArgumentException("Invalid game snapshot");
        }
        String[][] result = new String[RepositoryConstants.BOARD_SIZE_HEIGHT][RepositoryConstants.BOARD_SIZE_WIDTH];
        char[] snapshotAsArray = modelSnapshot.toCharArray();
        for (int y = 0; y < RepositoryConstants.BOARD_SIZE_HEIGHT; y++) {
            result[y] = new String[RepositoryConstants.BOARD_SIZE_WIDTH];
            for (int x = 0; x < RepositoryConstants.BOARD_SIZE_WIDTH; x++) {
                result[y][x] = String.valueOf(snapshotAsArray[y * RepositoryConstants.BOARD_SIZE_WIDTH + x]);
            }
        }
        return result;
    }
}
