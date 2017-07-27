package org.vmis.task.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vmis.task.dto.converter.GameBriefDtoConverter;
import org.vmis.task.dto.converter.GameFullDtoConverter;
import org.vmis.task.dto.model.GameBriefDto;
import org.vmis.task.dto.model.GameFullDto;
import org.vmis.task.model.Game;
import org.vmis.task.service.GameService;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameService gameService;

    private GameBriefDtoConverter briefDtoConverter;

    private GameFullDtoConverter fullDtoConverter;

    @Autowired
    public GameController(
        GameService gameService,
        GameBriefDtoConverter briefDtoConverter,
        GameFullDtoConverter fullDtoConverter) {
        this.gameService = gameService;
        this.briefDtoConverter = briefDtoConverter;
        this.fullDtoConverter = fullDtoConverter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAll() {
        List<Game> games = gameService.findAll();
        List<GameBriefDto> result = games.stream().map(briefDtoConverter::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable Long id) {
        Game game = gameService.findById(id);
        GameFullDto result = fullDtoConverter.toDto(game);
        return ResponseEntity.ok(result);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody GameBriefDto dto) {
        Game game  = gameService.add(dto.title);
        GameBriefDto result = briefDtoConverter.toDto(game);
        return ResponseEntity.ok(result);
    }
}
