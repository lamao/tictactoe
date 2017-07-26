package org.vmis.task.dto.model;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public class GameFullDto extends GameBaseDto {
    public StateDto state;

    public LocationDto lastTurn;

    public char[][] snapshot;
}
