package org.oobootcamp.warmup;

import java.util.List;

public class ParkingManager implements Parking {

    private final List<Parking> parkingList;

    public ParkingManager(List<Parking> parkingList) {
        this.parkingList = parkingList;
    }

    @Override
    public Ticket park(Car car) throws ParkingFullException {
        for (Parking lot : parkingList) {
            try {
                return lot.park(car);
            } catch (ParkingFullException ignored) {
            }
        }
        throw new ParkingFullException();
    }

    @Override
    public Car pickup(Ticket ticket) throws TicketInvalidException {
        for (Parking parkingLot : parkingList) {
            try {
                return parkingLot.pickup(ticket);
            } catch (RuntimeException ignored) {
            }
        }
        throw new TicketInvalidException();
    }
}
