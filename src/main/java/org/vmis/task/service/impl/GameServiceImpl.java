package org.vmis.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vmis.task.model.Game;
import org.vmis.task.model.State;
import org.vmis.task.repository.GameRepository;
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

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, StateRepository stateRepository, SnapshotService snapshotService) {
        this.gameRepository = gameRepository;
        this.stateRepository = stateRepository;
        this.snapshotService = snapshotService;
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
        game.setSnapshot(snapshotService.createInitialSnapshot());

        long newId = gameRepository.add(game);
        game.setId(newId);

        return game;
    }
}
