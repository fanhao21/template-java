package org.oobootcamp.warmup;

import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingDelegator {
    public SmartParkingBoy(List<ParkingManagement> parkingManagements) {
        super(parkingManagements);
    }

    @Override
    protected List<ParkingManagement> orderParkingManagements() {
        return parkingManagements.stream()
                .sorted((left, right) -> Integer.compare(right.vacancyNum(), left.vacancyNum()))
                .collect(Collectors.toList());
    }
}
