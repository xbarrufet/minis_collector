package net.barrufet.mc.master.exceptions;

public class FactionNotFoundException  extends RuntimeException{

    public FactionNotFoundException(String id) {
        super("Could not find faction " + id);
    }
}
