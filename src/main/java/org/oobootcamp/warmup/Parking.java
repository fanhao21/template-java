package org.oobootcamp.warmup;

public interface Parking {
    Ticket park(Car car) throws ParkingFullException;

    Car pickup(Ticket ticket) throws TicketInvalidException;
}
