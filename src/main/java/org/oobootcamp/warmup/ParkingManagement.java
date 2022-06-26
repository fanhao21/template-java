package org.oobootcamp.warmup;

public interface ParkingManagement {
    Ticket park(Car car) throws ParkingFullException;

    Car pickup(Ticket ticket) throws TicketInvalidException;

    int vacancyNum();
}
