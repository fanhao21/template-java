package org.oobootcamp.warmup;

import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected List<ParkingLot> orderParkingLots() {
        return parkingLots.stream()
                .sorted((left, right) -> Integer.compare(right.vacancyNum(), left.vacancyNum()))
                .collect(Collectors.toList());
    }
}
