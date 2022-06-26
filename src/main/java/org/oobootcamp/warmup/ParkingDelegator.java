package org.oobootcamp.warmup;

import java.util.List;

public abstract class ParkingDelegator implements ParkingManagement {
    protected final List<ParkingManagement> parkingManagements;

    protected ParkingDelegator(List<ParkingManagement> parkingManagements) {
        this.parkingManagements = parkingManagements;
    }

    @Override
    public Ticket park(Car car) throws ParkingFullException {
        for (ParkingManagement lot : orderParkingManagements()) {
            try {
                return lot.park(car);
            } catch (ParkingFullException ignored) {
            }
        }
        throw new ParkingFullException();
    }

    @Override
    public Car pickup(Ticket ticket) throws TicketInvalidException {
        for (ParkingManagement parkingLot : parkingManagements) {
            try {
                return parkingLot.pickup(ticket);
            } catch (RuntimeException ignored) {
            }
        }
        throw new TicketInvalidException();
    }

    @Override
    public int vacancyNum() {
        return parkingManagements.stream()
                .map(ParkingManagement::vacancyNum)
                .reduce(Integer::sum)
                .orElse(0);
    }

    protected abstract List<ParkingManagement> orderParkingManagements();
}
