package org.vmis.task.dto.model;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public class GameFullDto extends GameBaseDto {
    public StateDto state;

    public LocationDto lastTurn;

    // TODO: Configure converty char[][] to 2-dimensional array in json. Now it is converted to String[]
    public String[][] snapshot;
}
