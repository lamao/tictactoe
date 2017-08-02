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
import org.vmis.task.dto.converter.LocationDtoConverter;
import org.vmis.task.dto.converter.StateDtoConverter;
import org.vmis.task.dto.model.GameBriefDto;
import org.vmis.task.dto.model.GameFullDto;
import org.vmis.task.dto.model.LocationDto;
import org.vmis.task.dto.model.StateDto;
import org.vmis.task.model.Game;
import org.vmis.task.model.Location;
import org.vmis.task.model.State;
import org.vmis.task.service.GameService;
import org.vmis.task.service.LocationService;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameService gameService;
    private LocationService locationService;

    private GameBriefDtoConverter briefDtoConverter;
    private GameFullDtoConverter fullDtoConverter;
    private LocationDtoConverter locationDtoConverter;
    private StateDtoConverter stateDtoConverter;



    @Autowired
    public GameController(
        GameService gameService,
        LocationService locationService,
        GameBriefDtoConverter briefDtoConverter,
        GameFullDtoConverter fullDtoConverter,
        LocationDtoConverter locationDtoConverter,
        StateDtoConverter stateDtoConverter) {
        this.gameService = gameService;
        this.locationService = locationService;

        this.briefDtoConverter = briefDtoConverter;
        this.fullDtoConverter = fullDtoConverter;
        this.locationDtoConverter = locationDtoConverter;
        this.stateDtoConverter = stateDtoConverter;
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

    @PostMapping(value = "{id}/make-turn", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity makeTurn(@PathVariable Long id, @RequestBody LocationDto locationDto) {
        Location location = locationDtoConverter.fromDto(locationDto);
        State state = gameService.makeTurn(id, location);
        StateDto result = stateDtoConverter.toDto(state);
        return ResponseEntity.ok(result);
    }

    // TODO: Introduce History & HistoryItem entity
    @GetMapping(value = "{id}/turns", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTurnHistory(@PathVariable Long id) {
        List<Location> turns = locationService.getTurnHistory(id);
        List<LocationDto> result = turns.stream().map(locationDtoConverter::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
