package org.vmis.task.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.vmis.task.dto.converter.GameDtoConverter;
import org.vmis.task.dto.model.GameBriefDto;
import org.vmis.task.model.Game;
import org.vmis.task.service.GameService;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameService gameService;

    private GameDtoConverter converter;

    @Autowired
    public GameController(
                    GameService gameService,
                    GameDtoConverter gameDtoConverter) {
        this.gameService = gameService;
        this.converter = gameDtoConverter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findAll() {
        List<Game> games = gameService.findAll();
        List<GameBriefDto> result = games.stream().map(converter::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
