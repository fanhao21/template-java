package org.oobootcamp.warmup;

import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy extends AbstractParkingBoy {

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    Optional<ParkingLot> findParkingLot() {
        return parkingLots.stream()
                .filter(ParkingLot::available)
                .findFirst();
    }
}
