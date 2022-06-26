package org.oobootcamp.warmup;

public class ParkingFullException extends RuntimeException{
    public ParkingFullException() {
        super("停车场已满");
    }
}
