package org.oobootcamp.warmup;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements Parking {
    private final Map<Ticket, Car> ticketCarMap;
    private final Integer capacity;

    public ParkingLot(Integer capacity) {
        this.ticketCarMap = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public Ticket park(Car car) {
        if (vacancyNum() > 0) {
            Ticket ticket = new Ticket();
            ticketCarMap.put(ticket, car);
            return ticket;
        }
        throw new ParkingFullException();
    }

    @Override
    public Car pickup(Ticket ticket) {
        if (!ticketCarMap.containsKey(ticket)) {
            throw new TicketInvalidException();
        }
        Car car = ticketCarMap.get(ticket);
        ticketCarMap.remove(ticket);
        return car;
    }

    public int vacancyNum() {
        return capacity - ticketCarMap.size();
    }
}
