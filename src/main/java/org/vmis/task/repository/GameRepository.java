package org.vmis.task.repository;

import org.vmis.task.model.Game;

import java.util.List;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface GameRepository {
    List<Game> findAll();

    Game findById(Long id);

    long add(Game game);

    void update(Game game);

    void addTurn(Long id, Long locationId);
}
