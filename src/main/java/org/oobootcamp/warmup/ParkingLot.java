package org.oobootcamp.warmup;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<Ticket, Car> ticketCarMap;
    private final Integer capacity;

    public ParkingLot(Integer capacity) {
        this.ticketCarMap = new HashMap<>();
        this.capacity = capacity;
    }

    public Car pickUp(Ticket ticket) {
        if (!ticketCarMap.containsKey(ticket)) {
            throw new RuntimeException("此票无效，请检查");
        }
        Car car = ticketCarMap.get(ticket);
        ticketCarMap.remove(ticket);
        return car;
    }

    public Ticket park(Car car) {
        if (isFull()) {
            throw new RuntimeException("停车场已满");
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    private boolean isFull() {
        return ticketCarMap.size() == capacity;
    }
}
