package main.controller.comamnds;


public interface Command {
    String getName();
    void execute();
}