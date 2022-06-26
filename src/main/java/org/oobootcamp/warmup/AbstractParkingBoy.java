package org.oobootcamp.warmup;

import java.util.List;
import java.util.Optional;

public abstract class AbstractParkingBoy {

    protected final List<ParkingLot> parkingLots;

    public AbstractParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return findParkingLot()
                .map(parkingLot -> parkingLot.park(car))
                .orElseThrow(() -> new RuntimeException("停车场已满"));
    }

    public Car pickup(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.checkTicket(ticket))
                .findFirst()
                .map(parkingLot -> parkingLot.pickUp(ticket))
                .orElseThrow(() -> new RuntimeException("此票无效，请检查"));
    }

    abstract Optional<ParkingLot> findParkingLot();
}
