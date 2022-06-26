package org.oobootcamp.warmup;

import java.util.List;

public abstract class ParkingBoy implements Parking {
    protected final List<ParkingLot> parkingLots;

    protected ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket park(Car car) throws ParkingFullException {
        for (ParkingLot lot : orderParkingLots()) {
            try {
                return lot.park(car);
            } catch (ParkingFullException ignored) {
            }
        }
        throw new ParkingFullException();
    }

    @Override
    public Car pickup(Ticket ticket) throws TicketInvalidException {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.pickup(ticket);
            } catch (RuntimeException ignored) {
            }
        }
        throw new TicketInvalidException();
    }

    protected abstract List<ParkingLot> orderParkingLots();
}
