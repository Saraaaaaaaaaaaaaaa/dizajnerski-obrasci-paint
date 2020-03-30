package main.controller.comamnds;

import java.io.Serializable;

public interface Command extends Serializable{
    String getName();
    void execute();
    boolean hasTarget();
}