package org.vmis.task.model;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public class Game {
    private Long id;

    private State state;

    private String title;

    private Snapshot snapshot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }
}
