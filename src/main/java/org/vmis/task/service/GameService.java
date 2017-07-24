package org.vmis.task.service;

import org.vmis.task.model.Game;

import java.util.List;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface GameService {
    List<Game> findAll();

    Game findById(Long id);
}
