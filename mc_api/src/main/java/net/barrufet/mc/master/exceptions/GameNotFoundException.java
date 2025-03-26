package net.barrufet.mc.master.exceptions;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String id) {
        super("Could not find game " + id);
    }
}
