package org.oobootcamp.warmup;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<Ticket, Car> ticketCarMap;
    private final Integer capacity;

    public ParkingLot(Integer capacity) {
        this.ticketCarMap = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public Car pickUp(Ticket ticket) {
        if (!checkTicket(ticket)) {
            throw new RuntimeException("此票无效，请检查");
        }
        Car car = ticketCarMap.get(ticket);
        ticketCarMap.remove(ticket);
        return car;
    }

    public boolean checkTicket(Ticket ticket) {
        return ticketCarMap.containsKey(ticket);
    }

    public Ticket park(Car car) {
        if (available()) {
            Ticket ticket = new Ticket();
            ticketCarMap.put(ticket, car);
            return ticket;
        }
        throw new RuntimeException("停车场已满");
    }

    public boolean available() {
        return allowance() > 0;
    }

    public int allowance() {
        return capacity - ticketCarMap.size();
    }
}
