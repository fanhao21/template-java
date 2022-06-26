package org.oobootcamp.warmup;

import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends AbstractParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    Optional<ParkingLot> findParkingLot() {
        return parkingLots.stream()
                .sorted((left, right) -> Integer.compare(right.allowance(), left.allowance()))
                .filter(ParkingLot::available)
                .findFirst();
    }
}
