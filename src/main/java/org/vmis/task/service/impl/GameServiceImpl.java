package org.vmis.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vmis.task.model.Game;
import org.vmis.task.model.State;
import org.vmis.task.repository.GameRepository;
import org.vmis.task.service.GameService;

import java.util.List;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
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
        Game result = new Game();
        result.setState(new State());
        result.getState().setCode("IN_PROGRESS");
        result.setTitle(title);
        result.setId(1000L);

        return result;
    }
}
