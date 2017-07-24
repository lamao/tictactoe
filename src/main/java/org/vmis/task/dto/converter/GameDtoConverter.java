package org.vmis.task.dto.converter;

import org.vmis.task.dto.model.GameBriefDto;
import org.vmis.task.model.Game;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public class GameDtoConverter implements Converter<Game, GameBriefDto> {

    @Override
    public GameBriefDto toDto(Game game) {
        GameBriefDto dto = new GameBriefDto();
        dto.id = game.getId();
        // dto.state = game.getState().getCode();
        dto.title = game.getTitle();
        return dto;
    }

    @Override
    public Game fromDto(GameBriefDto gameBrief) {
        Game game = new Game();
        game.setId(gameBrief.id);
        game.setTitle(gameBrief.title);

        // State state = new State();
        // state.setCode(gameBrief.state);
        // game.setState(state);
        return game;
    }
}
