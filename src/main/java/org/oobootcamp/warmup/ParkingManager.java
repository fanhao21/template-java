package org.oobootcamp.warmup;

import java.util.List;

public class ParkingManager extends ParkingDelegator {

    public ParkingManager(List<ParkingManagement> parkingManagements) {
        super(parkingManagements);
    }

    @Override
    protected List<ParkingManagement> orderParkingManagements() {
        return parkingManagements;
    }
}
