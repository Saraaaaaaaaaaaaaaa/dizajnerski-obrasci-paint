package main.controller;


public interface Command {
    String getName();
    void execute();
}