package org.vmis.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vmis.task.model.Game;
import org.vmis.task.model.Location;
import org.vmis.task.model.Snapshot;
import org.vmis.task.model.State;
import org.vmis.task.repository.GameRepository;
import org.vmis.task.repository.LocationRepository;
import org.vmis.task.repository.RepositoryConstants;
import org.vmis.task.repository.SnapshotRepository;
import org.vmis.task.repository.StateRepository;
import org.vmis.task.service.GameService;
import org.vmis.task.service.SnapshotService;

import java.util.List;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private StateRepository stateRepository;
    private SnapshotService snapshotService;
    private LocationRepository locationRepository;
    private SnapshotRepository snapshotRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository,
                           StateRepository stateRepository,
                           SnapshotService snapshotService,
                           LocationRepository locationRepository,
                           SnapshotRepository snapshotRepository) {
        this.gameRepository = gameRepository;
        this.stateRepository = stateRepository;
        this.snapshotService = snapshotService;
        this.locationRepository = locationRepository;
        this.snapshotRepository = snapshotRepository;
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public Game add(String title) {
        State initialState = stateRepository.getInitialState();

        Game game = new Game();
        game.setState(initialState);
        game.setTitle(title);

        Snapshot snapshot = snapshotService.createInitialSnapshot();
        Long snapshotId = snapshotRepository.add(snapshot);
        snapshot.setId(snapshotId);
        game.setSnapshot(snapshot);

        long newId = gameRepository.add(game);
        game.setId(newId);

        return game;
    }

    @Override
    public State makeTurn(Long gameId, Location location) {
        Game game = gameRepository.findById(gameId);
        Snapshot snapshot = game.getSnapshot();
        char[][] dump = snapshot.getDump();
        if (dump[location.getY()][location.getX()] != RepositoryConstants.BOARD_EMPTY_CELL) {
            throw new IllegalArgumentException("Not empty cell");
        }

        dump[location.getY()][location.getX()] = getNextTurn(dump, snapshot.getLastTurn());

        Long locationId = locationRepository.add(location);
        location.setId(locationId);
        snapshot.setLastTurn(location);

        snapshotRepository.update(snapshot);

        State newState = calculateState(dump);
        if (!newState.getCode().equals(game.getState().getCode())) {
            game.setState(newState);
            gameRepository.update(game);
        }
        return newState;
    }

    private char getNextTurn(char[][] dump, Location lastTurn) {
        char result = RepositoryConstants.BOARD_X_CELL;
        if (lastTurn != null && dump[lastTurn.getY()][lastTurn.getX()] == RepositoryConstants.BOARD_X_CELL) {
            result = RepositoryConstants.BOARD_O_CELL;
        }
        return result;
    }

    private State calculateState(char[][] dump) {
        State state = stateRepository.findByCode("IN_PROGRESS");
        return state;
    }
}
